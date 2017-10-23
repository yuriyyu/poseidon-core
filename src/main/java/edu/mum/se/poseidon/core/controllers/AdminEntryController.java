package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.EntryDto;
import edu.mum.se.poseidon.core.controllers.mapper.EntryMapper;
import edu.mum.se.poseidon.core.controllers.wrapper.ErrorResponseWrapper;
import edu.mum.se.poseidon.core.controllers.wrapper.FailResponseWrapper;
import edu.mum.se.poseidon.core.repositories.models.Block;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.services.BlockService;
import edu.mum.se.poseidon.core.services.EntryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminEntryController {

    private EntryService entryService;
    private BlockService blockService;
    private static final Logger logger = LoggerFactory.getLogger(AdminEntryController.class);
    private EntryMapper entryMapper;

    @Autowired
    public AdminEntryController(EntryService entryService, EntryMapper entryMapper, BlockService blockService) {
        this.entryService = entryService;
        this.entryMapper = entryMapper;
        this.blockService = blockService;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/entries/create", method = RequestMethod.POST)
    public ResponseEntity<?> createEntry(@RequestBody EntryDto entryDto) {
        logger.debug("'Create entry' request is received. Entry name=" + entryDto.getName());
        try {
            Entry entry = entryMapper.getEntryFrom(entryDto);
            if (entry.getStartDate() != null && entry.getStartDate().isBefore(LocalDate.now())) {
                return new ResponseEntity("Start date is invalid.", HttpStatus.BAD_REQUEST);
            }
            if (entry.getStartDate() != null && entryService.getEntryByStartDate(entry.getStartDate()) != null) {
                return new ResponseEntity("Entry is already exist with that date.", HttpStatus.CONFLICT);
            }
            // for generate id in this entry
            entry = entryService.createEntry(entry);
            int totalNumber = calculateBlockNumber(entry);
            entry.setBlockList(blockService.autoGenerate(entry, totalNumber));
            entryService.editEntry(entry);
            return new ResponseEntity(entryMapper.getEntryDtoFrom(entry), HttpStatus.OK);
        } catch (PoseidonException pe) {
            logger.error(pe.getMessage());
            return new ResponseEntity(pe.getMessage(), pe.getHttpStatus());
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/entries/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteEntry(@PathVariable(name = "id") long id) {
        logger.info("'Delete entry' request is received. Entry ID=" + id);
        try {
            Entry entry = entryService.getEntry(id);
            if (entry == null) {
                logger.info("Entry is not found.");
                return new ResponseEntity("Entry is not found.", HttpStatus.NOT_FOUND);
            }
            if (entry.getStartDate() != null && entry.getStartDate().isBefore(LocalDate.now())) {
                logger.info("Entry is already started.");
                return new ResponseEntity("Entry is already started.", HttpStatus.BAD_REQUEST);
            }
            if (entry.getBlockList() != null) {
                entry.getBlockList().forEach(b -> blockService.deleteBlock(b.getId()));
            }
            entryService.deleteEntry(id);
            return new ResponseEntity(new EntryDto(), HttpStatus.OK);
        } catch (PoseidonException pe) {
            logger.error(pe.getMessage());
            return new ResponseEntity(pe.getMessage(), pe.getHttpStatus());
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/entries/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEntry(@PathVariable long id) {
        logger.info("'Get entry' request is received. Entry ID=" + id);
        try {
            EntryDto entryDto = entryMapper.getEntryDtoFrom(entryService.getEntry(id));
            if (entryDto == null) {
                logger.info("Entry is not found.");
                return new ResponseEntity("Entry is not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(entryDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/entries/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editEntry(@RequestBody EntryDto entryDto) {
        logger.info("'Edit entry' request is received. Entry name=" + entryDto.getName());
        try {
            Entry entry = entryMapper.getEntryFrom(entryDto);
            if (entry == null) {
                logger.info("Entry cannot be null.");
                return new ResponseEntity("Entry cannot be null.", HttpStatus.BAD_REQUEST);
            }
            if (entry.getStartDate() != null && entry.getStartDate().isBefore(LocalDate.now())) {
                logger.info("Date is invalid.");
                return new ResponseEntity("Date is invalid.", HttpStatus.BAD_REQUEST);
            }
            Entry entryExist = entryService.getEntry(entryDto.getId());
            if (entryExist == null) {
                logger.info("Entry is not found.");
                return new ResponseEntity("Entry is not found.", HttpStatus.NOT_FOUND);
            }
            if (entryExist.getStartDate() != null && entryExist.getStartDate().isBefore(LocalDate.now())) {
                logger.info("Entry is already started.");
                return new ResponseEntity("Entry is already started.", HttpStatus.BAD_REQUEST);
            }
            if (entryExist.getBlockList() != null) {
                entryExist.getBlockList().forEach(b -> blockService.deleteBlock(b.getId()));
            }
            int totalNumber = calculateBlockNumber(entry);
            entry.setBlockList(blockService.autoGenerate(entry, totalNumber));
            entryDto = entryMapper.getEntryDtoFrom(entryService.editEntry(entry));
            return new ResponseEntity<>(entryDto, HttpStatus.OK);
        } catch (PoseidonException pe) {
            logger.error(pe.getMessage());
            return new ResponseEntity(pe.getMessage(), pe.getHttpStatus());
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/entries", method = RequestMethod.GET)
    public ResponseEntity<?> getEntryList() {
        logger.info("'Get entries' request is received.");
        try {
            List<Entry> entryList = entryService.getEntryList();
            List<EntryDto> entryDtoList = entryMapper.getEntryDtoListFrom(entryList);
            return new ResponseEntity<>(entryDtoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int calculateBlockNumber(Entry entry) {
        int retVal = 0;
        int elective = 0;
        int sci = 1;
        int fpp = 0;
        int mpp = 1;
        int career = 1;
        if (entry == null) {
            return retVal;
        }
        if (entry.getnFppStudents() > 0) {
            fpp = 1;
        }
        if (entry.getnMppOpt() == 0 && entry.getnFppOpt() == 0) {
            elective = 4;
        } else if (entry.getnMppOpt() > 0) {
            elective = 5;
        } else if (entry.getnFppOpt() > 0) {
            elective = 5;
        } else if (entry.getUsRes() > 0) {
            elective = 8;
        }
        retVal = sci + fpp + mpp + elective + career;
        return retVal;
    }
}
