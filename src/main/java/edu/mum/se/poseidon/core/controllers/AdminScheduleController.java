package edu.mum.se.poseidon.core.controllers;

import edu.mum.se.poseidon.core.controllers.dto.ScheduleDto;
import edu.mum.se.poseidon.core.controllers.dto.ScheduleGenerateDto;
import edu.mum.se.poseidon.core.controllers.mapper.ScheduleMapper;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import edu.mum.se.poseidon.core.services.ScheduleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminScheduleController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private ScheduleService scheduleService;
    private ScheduleMapper scheduleMapper;

    @Autowired
    public AdminScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @RequestMapping(path = "/schedules", method = RequestMethod.GET)
    public ResponseEntity<?> getSchedules() {
        List<Schedule> schedules = scheduleService.getSchedules();
        List<ScheduleDto> dto = schedules.stream()
                .map(x -> scheduleMapper.getScheduleDto(x))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/schedules/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSchedule(@PathVariable long id){
        Schedule s = scheduleService.getScheduleById(id);
        ScheduleDto dto = scheduleMapper.getScheduleDto(s);
        return  new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/schedules/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(new ScheduleDto(), HttpStatus.OK);
    }


    @RequestMapping(path = "/schedules/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ScheduleDto scheduleDto) {
        Schedule schedule = this.scheduleService.createSchedule(scheduleDto);
        ScheduleDto udo = scheduleMapper.getScheduleDto(schedule);
        return new ResponseEntity<>(udo, HttpStatus.OK);
    }

    @RequestMapping(path = "/schedules/edit", method = RequestMethod.POST)
    public ResponseEntity<?> edit(@RequestBody ScheduleDto scheduleDto) {
        Schedule schedule = this.scheduleService.editSchedule(scheduleDto);
        ScheduleDto dto = scheduleMapper.getScheduleDto(schedule);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/schedules/generate", method = RequestMethod.POST)
    public ResponseEntity<?> generate(@RequestBody ScheduleGenerateDto dto) {
        Schedule schedule = this.scheduleService.generate(dto);

        // TODO: Complete Schedule Generation
        ScheduleDto scheduleDto = new ScheduleDto();// this.scheduleMapper.getScheduleDto(schedule);
        return new ResponseEntity<>(scheduleDto, HttpStatus.OK);
    }
}
