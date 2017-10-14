package edu.mum.se.poseidon.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.StudentRepository;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import edu.mum.se.poseidon.core.repositories.models.users.User;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	
	@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
	
	public Student createStudent(User user) {
		Student student = new Student();
		student = (Student) user;
		student = studentRepository.save(student);
		return student;
	}

	public Student getStudent(long studentId) {
		return studentRepository.findOne(studentId);
	}
	
}
