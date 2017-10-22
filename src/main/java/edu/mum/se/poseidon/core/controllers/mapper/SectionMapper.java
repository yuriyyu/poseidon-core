package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.configs.Helper;
import edu.mum.se.poseidon.core.controllers.dto.AdminSectionDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultySectionDto;
import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.controllers.dto.StudentSectionDto;
import edu.mum.se.poseidon.core.repositories.models.Section;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SectionMapper {

    public Section getSectionFrom(SectionDto sectionDto) {
        if (sectionDto == null) {
            return null;
        }
        Section section = new Section();
        section.setId(sectionDto.getId());
        section.setLocation(sectionDto.getLocation());
        section.setMaxSeats(sectionDto.getMaxSeats());
        return section;
    }

    public SectionDto getSectionDtoFrom(Section section) {
        if (section == null) {
            return null;
        }
        SectionDto sectionDto = new SectionDto();
        sectionDto.setBlockName(section.getBlock().getName());
        sectionDto.setEndDate(Helper.convertDateToString(section.getBlock().getEndDate()));
        sectionDto.setStartDate(Helper.convertDateToString(section.getBlock().getStartDate()));
        sectionDto.setFacultyFirstName(section.getFaculty().getFirstName());
        sectionDto.setFacultyLastName(section.getFaculty().getLastName());
        sectionDto.setId(section.getId());
        sectionDto.setLocation(section.getLocation());
        sectionDto.setMaxSeats(section.getMaxSeats());
        sectionDto.setName(section.getName());
        return sectionDto;
    }

    public List<StudentSectionDto> getStudentSectionDtoList(List<Section> sectionList) {
        if (sectionList == null) {
            return null;
        }

        return sectionList.stream()
                .map(s -> getStudentSectionDto(s))
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    public StudentSectionDto getStudentSectionDto(Section section) {
        if (section == null) {
            return null;
        }

        StudentSectionDto dto = new StudentSectionDto();
        dto.setBlockName(section.getBlock().getName());
        dto.setEndDate(Helper.convertDateToString(section.getBlock().getEndDate()));
        dto.setStartDate(Helper.convertDateToString(section.getBlock().getStartDate()));
        dto.setFacultyFirstName(section.getFaculty().getFirstName());
        dto.setFacultyLastName(section.getFaculty().getLastName());
        dto.setId(section.getId());
        dto.setLocation(section.getLocation());
        dto.setMaxSeats(section.getMaxSeats());
        dto.setCourseName(section.getCourse().getName());
        dto.setCourseNumber(section.getCourse().getNumber());

        return dto;
    }

    public List<Section> getSectionListFrom(List<SectionDto> sectionDtoList) {
        if (sectionDtoList == null || sectionDtoList.isEmpty()) {
            return null;
        }
        return sectionDtoList
                .stream()
                .map(s -> getSectionFrom(s))
                .collect(Collectors.toList());
    }

    public List<SectionDto> getSectionDtoListFrom(List<Section> sectionList) {
        if (sectionList == null || sectionList.isEmpty()) {
            return new ArrayList<>();
        }
        return sectionList
                .stream()
                .map(s -> getSectionDtoFrom(s))
                .collect(Collectors.toList());
    }

    public Map<Long, List<FacultySectionDto>> getFacultySectionDtoMap(List<Section> sectionList) {
        if (sectionList == null) {
            return null;
        }

        Map<Long, List<FacultySectionDto>> map = new HashMap<>();
        for (Section s : sectionList) {
            List<FacultySectionDto> list = map.get(s.getId());
            if (list == null) {
                list = new ArrayList<>();
                map.put(s.getId(), list);
            }

            FacultySectionDto dto = getFacultySectionDto(s);
            if (dto != null) {
                list.add(dto);
            }
        }

        return map;
    }

    public FacultySectionDto getFacultySectionDto(Section section) {
        if (section == null) {
            return null;
        }

        FacultySectionDto dto = new FacultySectionDto();
        dto.setBlockName(section.getBlock().getName());
        dto.setEndDate(Helper.convertDateToString(section.getBlock().getEndDate()));
        dto.setStartDate(Helper.convertDateToString(section.getBlock().getStartDate()));
        dto.setId(section.getId());
        dto.setLocation(section.getLocation());
        dto.setMaxSeats(section.getMaxSeats());
        dto.setCourseName(section.getCourse().getName());
        dto.setCourseNumber(section.getCourse().getNumber());

        return dto;
    }

    public List<AdminSectionDto> getAdminSectionDtoList(List<Section> sectionList) {
        if (sectionList == null) {
            return null;
        }

        return sectionList.stream()
                .map(s -> getAdminSectionDto(s))
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    public AdminSectionDto getAdminSectionDto(Section section) {
        if (section == null) {
            return null;
        }

        AdminSectionDto dto = new AdminSectionDto();
        dto.setBlockName(section.getBlock().getName());
        dto.setEndDate(Helper.convertDateToString(section.getBlock().getEndDate()));
        dto.setStartDate(Helper.convertDateToString(section.getBlock().getStartDate()));
        dto.setId(section.getId());
        dto.setLocation(section.getLocation());
        dto.setMaxSeats(section.getMaxSeats());
        dto.setCourseName(section.getCourse().getName());
        dto.setCourseNumber(section.getCourse().getNumber());
        dto.setFacultyFirstName(section.getFaculty().getFirstName());
        dto.setFacultyLastName(section.getFaculty().getLastName());

        return dto;
    }
}
