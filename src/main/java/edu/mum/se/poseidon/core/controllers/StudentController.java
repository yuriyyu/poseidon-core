package edu.mum.se.poseidon.core.controllers;


import edu.mum.se.poseidon.core.controllers.dto.StudentProfileDto;
import edu.mum.se.poseidon.core.controllers.mapper.StudentToDtoMapper;
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
	private StudentToDtoMapper studentToDtoMapper;
	
	@Autowired
	public StudentController(StudentService studentService, StudentToDtoMapper studentToDtoMapper){
		this.studentService = studentService;
		this.studentToDtoMapper = studentToDtoMapper;
	}
	
	@RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudentProfile(@PathVariable(name = "id") long id)
            throws Exception {
        Student student = studentService.getStudent(id);
        StudentProfileDto dto = studentToDtoMapper.getStudentDtoFrom(student);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/students/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateProfile(@PathVariable long id, StudentProfileDto studentProfileDto)
            throws Exception {
	    Student student = studentService.updateProfile(id, studentProfileDto);
	    StudentProfileDto dto = studentToDtoMapper.getStudentDtoFrom(student);

	    return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
