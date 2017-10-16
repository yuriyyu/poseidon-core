package edu.mum.se.poseidon.core.controllers;


import edu.mum.se.poseidon.core.controllers.dto.StudentDto;
import edu.mum.se.poseidon.core.controllers.mapper.StudentToDto;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
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
	private StudentToDto studentToDto;
	
	@Autowired
	public StudentController(StudentService studentService, StudentToDto studentToDto){
		this.studentService = studentService;
		this.studentToDto = studentToDto;
	}
	
	@RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudent(@PathVariable(name = "id") long id) {
        Student student = studentService.getStudent(id);
        StudentDto dto = studentToDto.getStudentDtoFrom(student);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
