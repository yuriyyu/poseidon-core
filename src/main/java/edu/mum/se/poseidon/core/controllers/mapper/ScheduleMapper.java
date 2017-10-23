package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.*;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import edu.mum.se.poseidon.core.repositories.models.Track;
import edu.mum.se.poseidon.core.services.Schedule.BlockTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public ScheduleDto getScheduleDtoByMap(Map<Track, List<BlockTrackDto>> map) {
        ScheduleDto dto = new ScheduleDto();
        dto.setMap(map);
        return  dto;
    }

    public StudentScheduleDto getStudentScheduleDto(Entry entry, List<StudentSectionDto> studentSectionDtoList) {
        StudentScheduleDto dto = new StudentScheduleDto();
        if(entry != null) {
            dto.setEntryName(entry.getName());
        }
        dto.setStudentSectionDtoList(studentSectionDtoList);

        return dto;
    }

    public List<FacultyScheduleDto> getFacultyScheduleDtoList(Map<Long, String> sectionToEntryNameMap,
                                                              Map<Long, List<FacultySectionDto>> facultySectionDtoMap) {

        List<FacultyScheduleDto> scheduleDtoList = new ArrayList<>();

        for(Map.Entry<Long,String> entry : sectionToEntryNameMap.entrySet()) {
            Long sectionId = entry.getKey();
            String entryName = entry.getValue();
            List<FacultySectionDto> list = facultySectionDtoMap.get(sectionId);

            scheduleDtoList.add(getFacultyScheduleDto(entryName, list));

        }
        return scheduleDtoList;
    }

    public FacultyScheduleDto getFacultyScheduleDto(String name, List<FacultySectionDto> list) {
        FacultyScheduleDto dto = new FacultyScheduleDto();
        dto.setEntryName(name);
        dto.setFacultySectionDtoList(list);

        return dto;
    }

    public AdminScheduleDto getAdminScheduleDto(String name, List<AdminSectionDto> list) {
        AdminScheduleDto dto = new AdminScheduleDto();
        dto.setEntryName(name);
        dto.setAdminSectionDtoList(list);

        return dto;
    }
}
