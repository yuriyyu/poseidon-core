package edu.mum.se.poseidon.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyDto;
import edu.mum.se.poseidon.core.controllers.mapper.CourseMapper;
import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.services.CourseService;

@Controller
public class AdminCourseController {

	private CourseService courseService;
	private CourseMapper courseMapper;
	private static final Logger log = LoggerFactory.getLogger(AdminCourseController.class);
	
	@Autowired
	public AdminCourseController(CourseService courseService, CourseMapper courseMapper) {
		this.courseService = courseService;
		this.courseMapper = courseMapper;
	}
	
	@RequestMapping(path="/courses/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody CourseDto courseDto) {
		Course course = courseService.createCourse(courseDto);
		CourseDto dto = courseMapper.getCourseDto(course);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(path="/courses/edit", method = RequestMethod.POST)
	public ResponseEntity<?> edit(@RequestBody CourseDto courseDto) {
		Course course = this.courseService.editCourse(courseDto);
		CourseDto dto = courseMapper.getCourseDto(course);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(path="/courses/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCourse(@PathVariable long id){
		Course course = courseService.getCourse(id);
		CourseDto dto = courseMapper.getCourseDto(course);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(path="/courses", method = RequestMethod.GET)
	public ResponseEntity<?> getCourseList(){
		List<Course> courses = courseService.getCourseList();
		List<CourseDto> dtos = courses.stream()
				.map(c -> courseMapper.getCourseDto(c))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(path="/courses/{id}/delete", method = RequestMethod.GET)
	public ResponseEntity<?> deleteCourse(@PathVariable long id){
		courseService.deleteCourse(id);
		return new ResponseEntity<>(new CourseDto(), HttpStatus.OK);
	}
}
