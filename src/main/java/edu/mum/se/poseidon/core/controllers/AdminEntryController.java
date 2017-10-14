package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.EntryDto;
import edu.mum.se.poseidon.core.controllers.mapper.EntryToDto;
import edu.mum.se.poseidon.core.services.EntryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminEntryController {

    private EntryService entryService;
    private static final Logger log = LoggerFactory.getLogger(AdminEntryController.class);
    private EntryToDto entryToDto;

    @Autowired
    public AdminEntryController(EntryService entryService, EntryToDto entryToDto) {
        this.entryService = entryService;
        this.entryToDto = entryToDto;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(path = "/entries/create", method = RequestMethod.POST)
    public ResponseEntity<?> createEntry(@RequestBody EntryDto entryDto) {
        log.debug("'Create entry' request is received. Entry name=" + entryDto.getName());
        return new ResponseEntity(
                entryService.createEntry(
                        entryDto.getStartDate(),
                        entryDto.getnFppStudents(),
                        entryDto.getnMppStudents(),
                        entryDto.getnFppOpt(),
                        entryDto.getnMppOpt(),
                        entryDto.getUsRes(),
                        entryDto.getName()),
                HttpStatus.OK);
    }
}
