package edu.mum.se.poseidon.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.StudentRepository;
import edu.mum.se.poseidon.core.repositories.models.users.Student;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	
	@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
	
	public Student createStudent(String username, String password) {
		Student student = new Student();
		student.setUsername(username);
		student.setPassword(password);
		student = studentRepository.save(student);
		return student;
	}

	public Student getStudent(long studentId) {
		return studentRepository.findOne(studentId);
	}
	
}
