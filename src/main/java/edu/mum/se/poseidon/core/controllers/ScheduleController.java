package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.*;
import edu.mum.se.poseidon.core.controllers.mapper.ScheduleMapper;
import edu.mum.se.poseidon.core.controllers.mapper.SectionMapper;
import edu.mum.se.poseidon.core.controllers.wrapper.ErrorResponseWrapper;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import edu.mum.se.poseidon.core.services.RegistrationSubsystem.RegistrationImpl;
import edu.mum.se.poseidon.core.services.ScheduleService;
import edu.mum.se.poseidon.core.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ScheduleController {

    private RegistrationImpl registrationImpl;
    private SectionMapper sectionMapper;
    private ScheduleMapper scheduleMapper;
    private StudentService studentService;
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(RegistrationImpl registrationImpl,
                              ScheduleMapper scheduleMapper,
                              StudentService studentService,
                              ScheduleService scheduleService,
                              SectionMapper sectionMapper) {
        this.registrationImpl = registrationImpl;
        this.sectionMapper = sectionMapper;
        this.scheduleMapper = scheduleMapper;
        this.studentService = studentService;
        this.scheduleService = scheduleService;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/students/{studentId}/schedule", method = RequestMethod.GET)
    public ResponseEntity<?> getStudentSchedule(@PathVariable(name = "studentId") long studentId) {
        try {
            List<Section> sectionList = registrationImpl.getRegisteredSectionByStudent(studentId);
            List<StudentSectionDto> studentSectionDtoList = sectionMapper.getStudentSectionDtoList(sectionList);

            Student student = studentService.getStudent(studentId);
            Entry entry = student.getEntry();
            StudentScheduleDto dto = scheduleMapper.getStudentScheduleDto(entry.getName(), studentSectionDtoList);

            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper("Failed to execute a request.");
            return new ResponseEntity(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(path = "/faculties/{facultyId}/schedule", method = RequestMethod.GET)
    public ResponseEntity<?> getFacultySchedule(@PathVariable(name = "facultyId") long facultyId) {
        try {
            List<Section> sectionList = registrationImpl.getSectionByFaculty(facultyId);

            Map<Long, List<FacultySectionDto>> facultySectionDtoMap
                    = sectionMapper.getFacultySectionDtoMap(sectionList);

            Map<Long, String> sectionToEntryNameMap = sectionList.stream()
                    .collect(Collectors.toMap(s -> s.getId(), s -> s.getBlock().getEntry().getName()));

            List<FacultyScheduleDto> facultyScheduleDtoList = scheduleMapper.getFacultyScheduleDtoList(sectionToEntryNameMap, facultySectionDtoMap);

            return new ResponseEntity(facultyScheduleDtoList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper("Failed to execute a request.");
            return new ResponseEntity(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(path = "/schedules/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSchedules() {
        try {
            List<Schedule> scheduleList = scheduleService.getSchedules();

            List<AdminScheduleDto> scheduleDtoList = new ArrayList<>();
            for(Schedule s : scheduleList) {
                String entryName = s.getEntry().getName();
                List<AdminSectionDto> sectionDtoList = sectionMapper.getAdminSectionDtoList(s.getSections());

                AdminScheduleDto scheduleDto = scheduleMapper.getAdminScheduleDto(entryName,sectionDtoList);
                scheduleDtoList.add(scheduleDto);
            }

            return new ResponseEntity(scheduleDtoList, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper("Failed to execute a request.");
            return new ResponseEntity(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
