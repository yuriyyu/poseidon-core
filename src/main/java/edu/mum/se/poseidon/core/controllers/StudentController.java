package edu.mum.se.poseidon.core.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.services.StudentService;
import edu.mum.se.poseidon.core.repositories.models.users.User;

@Controller
public class StudentController {

	private StudentService studentService;
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	public StudentController(StudentService studentSerive){
		this.studentService = studentSerive;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path="/student/create", method = RequestMethod.PUT)
	public ResponseEntity<?> create(@RequestBody User user) {
		
		log.info(user.getFirstName());
		return new ResponseEntity(this.studentService.createStudent(user),
				HttpStatus.NOT_FOUND);
	}
}
