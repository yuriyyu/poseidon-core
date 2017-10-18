package edu.mum.se.poseidon.core.controllers.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.repositories.models.Course;

@Component
public class CourseMapper {

	private FacultyMapper facultyMapper;
	private PrerequisiteMapper prerequisiteMapper;
	
	@Autowired
	public CourseMapper(FacultyMapper facultyMapper, PrerequisiteMapper prerequisiteMapper) {
		this.facultyMapper = facultyMapper;
		this.prerequisiteMapper = prerequisiteMapper;
	}
	
	public CourseDto getCourseDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setName(course.getName());
		courseDto.setNumber(course.getNumber());
		
		courseDto.setPrerequisites(course.getPrerequisites()
					.stream()
					.map(p -> prerequisiteMapper.getPrerequisiteDto(p))
					.collect(Collectors.toList()));
		courseDto.setFaculties(course.getFaculties()
					.stream()
					.map(f -> facultyMapper.getFacultyDto(f))
					.collect(Collectors.toList()));
		return courseDto;
	}
}
