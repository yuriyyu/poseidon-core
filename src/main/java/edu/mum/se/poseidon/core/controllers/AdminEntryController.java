package edu.mum.se.poseidon.core.controllers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import edu.mum.se.poseidon.core.controllers.dto.EntryDto;
import edu.mum.se.poseidon.core.controllers.mapper.EntryMapper;
import edu.mum.se.poseidon.core.controllers.wrapper.FailResponseWrapper;
import edu.mum.se.poseidon.core.repositories.models.Entry;
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

@Controller
public class AdminEntryController {

    private EntryService entryService;
    private static final Logger log = LoggerFactory.getLogger(AdminEntryController.class);
    private EntryMapper entryMapper;

    @Autowired
    public AdminEntryController(EntryService entryService, EntryMapper entryMapper) {
        this.entryService = entryService;
        this.entryMapper = entryMapper;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(path = "/entry/create", method = RequestMethod.POST)
    public ResponseEntity<?> createEntry(@RequestBody EntryDto entryDto) {
        log.debug("'Create entry' request is received. Entry name=" + entryDto.getName());
        Entry entry = entryMapper.getEntryFrom(entryDto);
        try {
            EntryDto entryDto1 = entryMapper.getEntryDtoFrom(entryService.createEntry(entry));
            return new ResponseEntity(entryDto1, HttpStatus.OK);
        } catch (InvalidArgumentException iae) {
            FailResponseWrapper failResponse = new FailResponseWrapper(iae.getMessage());
            return new ResponseEntity(failResponse, HttpStatus.CONFLICT);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(path = "/entry/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEntry(@PathVariable(name = "id") long id) {
        log.debug("'Delete entry' request is received. Entry name=" + id);
        try {
            EntryDto entryDto1 = entryMapper.getEntryDtoFrom(entryService.deleteEntry(id));
            return new ResponseEntity(entryDto1, HttpStatus.OK);
        } catch (InvalidArgumentException iae) {
            FailResponseWrapper failResponse = new FailResponseWrapper(iae.getMessage());
            return new ResponseEntity(failResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
