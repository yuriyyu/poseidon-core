package edu.mum.se.poseidon.core.controllers.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.repositories.models.Block;
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
					.map(f -> facultyMapper.getFacultyProfileDtoFrom(f))
					.collect(Collectors.toList()));
		return courseDto;
	}
	
	public Course getCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setId(courseDto.getId());
		course.setName(courseDto.getName());
		course.setNumber(courseDto.getNumber());
		
		course.setPrerequisites(courseDto.getPrerequisites()
					.stream()
					.map(p -> prerequisiteMapper.getCourse(p))
					.collect(Collectors.toList()));
		course.setFaculties(courseDto.getFaculties()
					.stream()
					.map(f -> facultyMapper.getFaculty(f))
					.collect(Collectors.toList()));
		return course;
	}
}
