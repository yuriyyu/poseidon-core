package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.ScheduleDto;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    @Autowired
    private SectionMapper sectionMapper;

    public ScheduleDto getScheduleDto(Schedule schedule) {
        if (schedule == null)
            return null;

        ScheduleDto dto = new ScheduleDto();
        dto.setName(schedule.getName());
        dto.setId(schedule.getId());
        dto.setStatus(schedule.getStatus());
        dto.setSections(schedule.getSections()
                .stream()
                .map(x -> sectionMapper.getSectionDtoFrom(x))
                .collect(Collectors.toList()));
        return dto;
    }

    public Schedule fromScheduleDto(ScheduleDto dto) {
        if (dto == null)
            return null;

        Schedule schedule = new Schedule();

        schedule.setId(dto.getId());
        schedule.setName(dto.getName());
        schedule.setStatus(dto.getStatus());
        schedule.setSections(dto.getSections()
                .stream()
                .map(x -> sectionMapper.getSectionFrom(x))
                .collect(Collectors.toList()));

        return schedule;
    }
}
