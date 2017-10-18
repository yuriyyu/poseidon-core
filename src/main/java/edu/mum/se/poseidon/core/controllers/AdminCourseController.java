package edu.mum.se.poseidon.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.controllers.mapper.CourseMapper;
import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.services.CourseService;

@Controller
public class AdminCourseController {

	private CourseService courseService;
	private CourseMapper courseMapper;
	
	@Autowired
	public AdminCourseController(CourseService courseService, CourseMapper courseMapper) {
		this.courseService = courseService;
		this.courseMapper = courseMapper;
	}
	
	@RequestMapping(path="/courses", method = RequestMethod.GET)
	public ResponseEntity<?> getCourseList(){
		List<Course> courses = courseService.getCourseList();
		List<CourseDto> dtos = courses.stream()
				.map(c -> courseMapper.getCourseDto(c))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
