package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.dto.StudentProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.StudentRepository;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	
	@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
	
	public Student getStudent(long studentId) {
		return studentRepository.findOne(studentId);
	}

	@Transactional
    public Student updateProfile(long studentId, StudentProfileDto studentProfileDto) {
        Student student = getStudent(studentId);
        if(student == null) {
            throw new RuntimeException("User not found");
        }

        student.setFirstName(studentProfileDto.getFirstName());
        student.setLastName(studentProfileDto.getLastName());
        student.setUsername(studentProfileDto.getUsername());
        student.setPassword(studentProfileDto.getPassword());

        student = studentRepository.save(student);

	    return student;
    }
}
