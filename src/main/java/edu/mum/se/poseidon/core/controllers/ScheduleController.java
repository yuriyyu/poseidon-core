package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.controllers.dto.StudentSectionDto;
import edu.mum.se.poseidon.core.controllers.mapper.SectionMapper;
import edu.mum.se.poseidon.core.controllers.wrapper.ErrorResponseWrapper;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.services.RegistrationSubsystem.RegistrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ScheduleController {

    private RegistrationImpl registrationImpl;
    private SectionMapper sectionMapper;

    @Autowired
    public ScheduleController(RegistrationImpl registrationImpl,
                              SectionMapper sectionMapper) {
        this.registrationImpl = registrationImpl;
        this.sectionMapper = sectionMapper;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/students/{studentId}/schedule", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentSchedule(@PathVariable(name = "studentId") long studentId) {
        try {
            List<Section> sectionList = registrationImpl.getRegisteredSectionByStudent(studentId);

            List<StudentSectionDto> studentSectionDtoList = sectionMapper.getStudentSectionDtoList(sectionList);

            return new ResponseEntity(studentSectionDtoList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper("Failed to execute a request.");
            return new ResponseEntity(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
