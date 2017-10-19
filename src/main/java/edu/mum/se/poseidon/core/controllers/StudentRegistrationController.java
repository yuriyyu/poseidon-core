package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.controllers.mapper.SectionMapper;
import edu.mum.se.poseidon.core.controllers.wrapper.ErrorResponseWrapper;
import edu.mum.se.poseidon.core.controllers.wrapper.FailResponseWrapper;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.services.RegistrationSubsystem.RegistrationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentRegistrationController {

    private RegistrationImpl registrationImpl;
    private static final Logger log = LoggerFactory.getLogger(AdminEntryController.class);
    private SectionMapper sectionMapper;

    public StudentRegistrationController(RegistrationImpl registrationImpl, SectionMapper sectionMapper) {
        this.registrationImpl = registrationImpl;
        this.sectionMapper = sectionMapper;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/student/{studentId}/section/available", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailableSections(@PathVariable(name = "studentId") long studentId) {
        try {
            List<Section> sectionList = registrationImpl.getAvailableSections(studentId);
            List<SectionDto> sectionDtoList = sectionList
                    .stream()
                    .map(s -> sectionMapper.getSectionDtoFrom(s))
                    .collect(Collectors.toList());
            return new ResponseEntity(sectionDtoList, HttpStatus.OK);
        } catch (PoseidonException pe) {
            FailResponseWrapper failResponse = new FailResponseWrapper(pe.getMessage());
            return new ResponseEntity(failResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper("Failed to execute a request.");
            return new ResponseEntity(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/student/{studentId}/section/{sectionId}", method = RequestMethod.GET)
    public ResponseEntity<?> registerToSection(@PathVariable(name = "studentId") long studentId,
                                               @PathVariable(name = "sectionId") long sectionId) {
        try {
            registrationImpl.registerToSection(studentId, sectionId);
            return new ResponseEntity(new SectionDto(), HttpStatus.OK);
        } catch (PoseidonException pe) {
            FailResponseWrapper failResponse = new FailResponseWrapper(pe.getMessage());
            return new ResponseEntity(failResponse, HttpStatus.CONFLICT);
        } catch (Exception e) {
            ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper("Failed to execute a request.");
            return new ResponseEntity(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
