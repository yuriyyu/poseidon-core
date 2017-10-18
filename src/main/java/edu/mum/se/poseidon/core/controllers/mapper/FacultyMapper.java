package edu.mum.se.poseidon.core.controllers.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyDto;
import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

@Component
public class FacultyMapper {

	public FacultyDto getFacultyDto(Faculty faculty) {
		FacultyDto facultyDto = new FacultyDto();
		
		facultyDto.setId(faculty.getId());
		facultyDto.setFirstName(faculty.getFirstName());
		facultyDto.setLastName(faculty.getLastName());
		facultyDto.setUsername(faculty.getUsername());
		facultyDto.setPassword(faculty.getPassword());
		/*
		List<CourseDto> courseDtos = faculty.getCourses().stream()
											.map(c -> courseMapper.getCourseDto(c))
											.collect(Collectors.toList());
		
		List<SectionDto> sectionDtos = faculty.getSections().stream()
				.map(s -> sectionMapper.getSectionDtoFrom(s))
				.collect(Collectors.toList());

		
		facultyDto.setCourses(courseDtos);
		facultyDto.setSections(sectionDtos);
		*/
		return facultyDto;
	}
}
