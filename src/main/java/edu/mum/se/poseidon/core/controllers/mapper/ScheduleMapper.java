package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.ScheduleDto;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import edu.mum.se.poseidon.core.repositories.models.Track;
import edu.mum.se.poseidon.core.services.Schedule.BlockTrack;
import javafx.concurrent.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
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

        if (schedule.getSections() != null && schedule.getSections().size() != 0)
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

        if (dto.getSections() != null && dto.getSections().size() != 0)
            schedule.setSections(dto.getSections()
                    .stream()
                    .map(x -> sectionMapper.getSectionFrom(x))
                    .collect(Collectors.toList()));

        return schedule;
    }

    public ScheduleDto getScheduleDtoByMap(Map<Track, List<BlockTrack>> map) {
        ScheduleDto dto = new ScheduleDto();
        dto.setMap(map);
        return  dto;
    }
}
