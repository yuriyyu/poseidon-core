package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.services.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
public class EntryController {
	
    private EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(path="/entries", method = RequestMethod.POST)
    public ResponseEntity<?> createEntry(LocalDate startDate, Integer nFppStudents, Integer nMppStudents, Integer nFppOpt,
                                         Integer nMppOpt, Integer usRes) {
        return new ResponseEntity(entryService.createEntry(startDate, nFppStudents, nMppStudents, nFppOpt, nMppOpt, usRes),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
