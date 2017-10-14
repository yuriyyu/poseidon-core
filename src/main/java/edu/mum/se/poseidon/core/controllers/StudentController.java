package edu.mum.se.poseidon.core.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.mum.se.poseidon.core.controllers.dto.StudentDto;
import edu.mum.se.poseidon.core.controllers.mapper.StudentToDto;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
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
	private StudentToDto studentToDto;
	
	@Autowired
	public StudentController(StudentService studentService, StudentToDto studentToDto){
		this.studentService = studentService;
		this.studentToDto = studentToDto;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(path="/student/create", method = RequestMethod.PUT)
	public ResponseEntity<?> create(@RequestBody User user) {
		
		log.info(user.getFirstName());
		Student student = this.studentService.createStudent(user);
		return new ResponseEntity(student, HttpStatus.OK);
	}

	@RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudent(@PathVariable(name = "id", required = true) long id) {
        Student student = studentService.getStudent(id);
        StudentDto dto = studentToDto.getStudentDtoFrom(student);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
