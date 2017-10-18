package edu.mum.se.poseidon.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.controllers.dto.FacultyDto;
import edu.mum.se.poseidon.core.controllers.mapper.FacultyMapper;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import edu.mum.se.poseidon.core.services.FacultyService;

@Controller
public class FacultyController {

	private FacultyService facultyService;
	private FacultyMapper facultyMapper;
	
	@Autowired
	public FacultyController(FacultyService facultyService, FacultyMapper facultyMapper) {
		this.facultyService = facultyService;
		this.facultyMapper = facultyMapper;
	}
	
	@RequestMapping(path="/faculties", method = RequestMethod.GET)
	public ResponseEntity<?> getFacultyList(){
		List<Faculty> faculties = facultyService.getFacultyList();
		List<FacultyDto> dtos = faculties.stream()
				.map(b -> facultyMapper.getFacultyDto(b))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
