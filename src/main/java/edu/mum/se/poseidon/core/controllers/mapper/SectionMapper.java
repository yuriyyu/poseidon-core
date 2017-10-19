package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.configs.Helper;
import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.controllers.dto.StudentSectionDto;
import edu.mum.se.poseidon.core.repositories.models.Section;
import org.springframework.stereotype.Component;

import java.util.List;
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
        sectionDto.setEndDate(Helper.convertDateToString(section.getEndDate()));
        sectionDto.setStartDate(Helper.convertDateToString(section.getStartDate()));
        sectionDto.setFacultyFirstName(section.getFaculty().getFirstName());
        sectionDto.setFacultyLastName(section.getFaculty().getLastName());
        sectionDto.setId(section.getId());
        sectionDto.setLocation(section.getLocation());
        sectionDto.setMaxSeats(section.getMaxSeats());
        return sectionDto;
    }

    public List<StudentSectionDto> getStudentSectionDtoList(List<Section> sectionList) {
        if(sectionList == null) {
            return null;
        }

        return sectionList.stream()
                .map(s -> getStudentSectionDto(s))
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    public StudentSectionDto getStudentSectionDto(Section section) {
        if(section == null) {
            return null;
        }

        StudentSectionDto dto = new StudentSectionDto();
        dto.setBlockName(section.getBlock().getName());
        dto.setEndDate(Helper.convertDateToString(section.getEndDate()));
        dto.setStartDate(Helper.convertDateToString(section.getStartDate()));
        dto.setFacultyFirstName(section.getFaculty().getFirstName());
        dto.setFacultyLastName(section.getFaculty().getLastName());
        dto.setId(section.getId());
        dto.setLocation(section.getLocation());
        dto.setMaxSeats(section.getMaxSeats());
        dto.setCourseName(section.getCourse().getName());
        dto.setCourseNumber(section.getCourse().getNumber());

        return dto;
    }
}
