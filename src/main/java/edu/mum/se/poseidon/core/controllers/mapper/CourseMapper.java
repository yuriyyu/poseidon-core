package edu.mum.se.poseidon.core.controllers.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyDto;
import edu.mum.se.poseidon.core.controllers.dto.SectionDto;
import edu.mum.se.poseidon.core.repositories.models.Course;

@Component
public class CourseMapper {

	public CourseDto getCourseDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setName(course.getName());
		courseDto.setNumber(course.getNumber());
		
		
		
		//courseDto.setPrerequisites(course.getPrerequisites());
		/*
		List<FacultyDto> facultyDtos = course.getFaculties().stream()
				.map(f -> facultyMapper.getFacultyDto(f))
				.collect(Collectors.toList());
		
		courseDto.setFaculties(facultyDtos);
		
		List<SectionDto> sectionDtos = course.getSections().stream()
				.map(s -> sectionMapper.getSectionDtoFrom(s))
				.collect(Collectors.toList());
		
		courseDto.setSections(sectionDtos);
		*/
		return courseDto;
	}
}
