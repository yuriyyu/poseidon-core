package edu.mum.se.poseidon.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.controllers.dto.CourseInfoDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyProfileDto;
import edu.mum.se.poseidon.core.controllers.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.controllers.mapper.FacultyMapper;
import edu.mum.se.poseidon.core.repositories.models.Block;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import edu.mum.se.poseidon.core.services.FacultyService;

@Controller
public class FacultyController {

	private FacultyService facultyService;
	private FacultyMapper facultyMapper;
	private CourseMapper courseMapper;
	
	@Autowired
	public FacultyController(FacultyService facultyService,
                             FacultyMapper facultyMapper,
                             CourseMapper courseMapper) {
		this.facultyService = facultyService;
		this.facultyMapper = facultyMapper;
		this.courseMapper = courseMapper;
	}
	
	@RequestMapping(path="/faculties", method = RequestMethod.GET)
	public ResponseEntity<?> getFacultyList(){
		List<Faculty> faculties = facultyService.getFacultyList();
		// TODO It should be list of some other objects -- Yuriy
		List<FacultyProfileDto> dtos = null;
//		List<FacultyProfileDto> dtos = faculties.stream()
//				.map(f -> facultyMapper.getFacultyProfileDtoFrom(f))
//				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@RequestMapping(path = "/faculties/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getFacultyProfile(@PathVariable(name = "id") long id)
			throws Exception {
		Faculty faculty = facultyService.getFaculty(id);
		List<CourseInfoDto> courseInfoDtoList = courseMapper.getCourseInfoDtoList(faculty.getCourses());
		FacultyProfileDto dto = facultyMapper.getFacultyProfileDtoFrom(faculty, courseInfoDtoList);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(path = "/faculties/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateProfile(@PathVariable long id, @RequestBody FacultyProfileDto profileDto)
			throws Exception {
        Faculty faculty = facultyService.updateProfile(id, profileDto);
		List<CourseInfoDto> courseInfoDtoList = courseMapper.getCourseInfoDtoList(faculty.getCourses());
        FacultyProfileDto dto = facultyMapper.getFacultyProfileDtoFrom(faculty, courseInfoDtoList);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	/* USED FOR ADMIN */
	@RequestMapping(path="/facultylist", method = RequestMethod.GET)
	public ResponseEntity<?> getFaculties(){
		List<Faculty> faculties = facultyService.getFacultyList();
		List<FacultyDto> dtos = faculties.stream()
				.map(f -> facultyMapper.getFacultyDto(f))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(path="/facultylist/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAFaculty(@PathVariable long id){
		Faculty faculty = facultyService.getFaculty(id);
		FacultyDto bdo = facultyMapper.getFacultyDto(faculty);
		return new ResponseEntity<>(bdo, HttpStatus.OK);
	}
}
