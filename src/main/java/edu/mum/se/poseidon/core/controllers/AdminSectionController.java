package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.controllers.mapper.SectionMapper;
import edu.mum.se.poseidon.core.controllers.wrapper.ErrorResponseWrapper;
import edu.mum.se.poseidon.core.controllers.wrapper.FailResponseWrapper;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.services.SectionService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminSectionController {

    private SectionService sectionService;
    private static final Logger logger = LoggerFactory.getLogger(AdminSectionController.class);
    private SectionMapper sectionMapper;

    @Autowired
    public AdminSectionController(SectionService sectionService, SectionMapper sectionMapper) {
        this.sectionService = sectionService;
        this.sectionMapper = sectionMapper;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/sections/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteSection(@PathVariable(name = "id") long id) {
        logger.info("'Delete section' request is received. Section ID=" + id);
        try {
            Section section = sectionService.getSection(id);
            if (section == null) {
                logger.info("Section is not found.");
                return new ResponseEntity("Section is not found.", HttpStatus.NOT_FOUND);
            }
            if (section.getBlock() != null && section.getBlock().getStartDate() != null
                    && section.getBlock().getStartDate().isBefore(LocalDate.now())) {
                logger.info("Section is already started.");
                return new ResponseEntity("Section is already started.", HttpStatus.BAD_REQUEST);
            }
            sectionService.deleteSection(id);
            return new ResponseEntity(new SectionDto(), HttpStatus.OK);
        } catch (PoseidonException pe) {
            logger.error(pe.getMessage(), pe);
            return new ResponseEntity(pe.getMessage(), pe.getHttpStatus());
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/sections/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSection(@PathVariable long id) {
        logger.info("'Get section' request is received. section ID=" + id);
        try {
            SectionDto sectionDto = sectionMapper.getSectionDtoFrom(sectionService.getSection(id));
            if (sectionDto == null) {
                logger.info("Section is not found.");
                return new ResponseEntity("Section is not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(sectionDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/sections/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editSection(@RequestBody SectionDto sectionDto) {
        logger.info("'Edit section' request is received. section ID=" + sectionDto.getId());
        try {
            Section section = sectionMapper.getSectionFrom(sectionDto);
            if (section == null) {
                logger.info("Section cannot be null");
                return new ResponseEntity("Section cannot be null", HttpStatus.BAD_REQUEST);
            }
            Section sectionExist = sectionService.getSection(sectionDto.getId());
            if (sectionExist == null) {
                logger.info("Section is not found.");
                return new ResponseEntity("Section is not found.", HttpStatus.NOT_FOUND);
            }
            if (sectionExist.getBlock() != null && sectionExist.getBlock().getStartDate() != null
                    && sectionExist.getBlock().getStartDate().isBefore(LocalDate.now())) {
                logger.info("Section is already started.");
                return new ResponseEntity("Section is already started.", HttpStatus.BAD_REQUEST);
            }
            sectionDto = sectionMapper.getSectionDtoFrom(sectionService.editSection(section));
            return new ResponseEntity<>(sectionDto, HttpStatus.OK);
        } catch (PoseidonException pe) {
            logger.error(pe.getMessage(), pe);
            return new ResponseEntity(pe.getMessage(), pe.getHttpStatus());
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/sections", method = RequestMethod.GET)
    public ResponseEntity<?> getSectionList() {
        logger.info("'Get sections' request is received.");
        try {
            List<Section> sectionList = sectionService.getSectionList();
            List<SectionDto> sectionDtoList = sectionMapper.getSectionDtoListFrom(sectionList);
            return new ResponseEntity<>(sectionDtoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
