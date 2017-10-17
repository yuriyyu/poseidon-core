package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.configs.Helper;
import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.repositories.models.Section;
import org.springframework.stereotype.Component;

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
}
