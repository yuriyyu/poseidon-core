package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.controllers.mapper.SectionMapper;
import edu.mum.se.poseidon.core.controllers.wrapper.ErrorResponseWrapper;
import edu.mum.se.poseidon.core.controllers.wrapper.FailResponseWrapper;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.services.RegistrationSubsystem.RegistrationImpl;
import edu.mum.se.poseidon.core.services.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentRegistrationController {

    private RegistrationImpl registrationImpl;
    private SectionService sectionService;
    private static final Logger logger = LoggerFactory.getLogger(AdminEntryController.class);
    private SectionMapper sectionMapper;

    public StudentRegistrationController(RegistrationImpl registrationImpl, SectionMapper sectionMapper,
                                         SectionService sectionService) {
        this.registrationImpl = registrationImpl;
        this.sectionMapper = sectionMapper;
        this.sectionService = sectionService;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/student/{studentId}/section/available", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailableSections(@PathVariable(name = "studentId") long studentId) {
        logger.info("'getAvailableSections' request is received. StudentId=" + studentId);
        try {
            List<Section> sectionList = registrationImpl.getAvailableSections(studentId);
            List<SectionDto> sectionDtoList = sectionMapper.getSectionDtoListFrom(sectionList);
            return new ResponseEntity(sectionDtoList, HttpStatus.OK);
        } catch (PoseidonException pe) {
            logger.error(pe.getMessage(), pe);
            return new ResponseEntity(pe.getMessage(), pe.getHttpStatus());
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/student/{studentId}/section/registered", method = RequestMethod.GET)
    public ResponseEntity<?> getRegisteredSectionByStudent(@PathVariable(name = "studentId") long studentId) {
        logger.info("'getRegisteredSectionByStudent' request is received.");
        try {
            List<Section> sectionList = sectionService.getRegisteredSectionByStudent(studentId);
            List<SectionDto> sectionDtoList = sectionMapper.getSectionDtoListFrom(sectionList);
            return new ResponseEntity(sectionDtoList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/student/{studentId}/section/{sectionId}", method = RequestMethod.GET)
    public ResponseEntity<?> registerToSection(@PathVariable(name = "studentId") long studentId,
                                               @PathVariable(name = "sectionId") long sectionId) {
        logger.info("'registerToSection' request is received.");
        try {
            registrationImpl.registerToSection(studentId, sectionId);
            return new ResponseEntity(new SectionDto(), HttpStatus.OK);
        } catch (PoseidonException pe) {
            logger.error(pe.getMessage(), pe);
            return new ResponseEntity(pe.getMessage(), pe.getHttpStatus());
        } catch (Exception e) {
            logger.error(PoseidonException.FAIL_MESSAGE, e);
            return new ResponseEntity(PoseidonException.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
