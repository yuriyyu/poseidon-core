package edu.mum.se.poseidon.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.services.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentSerive){
		this.studentService = studentSerive;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path="/students", method = RequestMethod.POST)
	public ResponseEntity<?> create(@PathVariable String username, 
				@PathVariable String password) {
		return new ResponseEntity(this.studentService.createStudent(username, password),
				HttpStatus.NOT_FOUND);
	}
}
