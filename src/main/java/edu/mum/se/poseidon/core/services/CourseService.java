package edu.mum.se.poseidon.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.mum.se.poseidon.core.repositories.CourseRepository;
import edu.mum.se.poseidon.core.repositories.models.Course;

@Service
public class CourseService {

	private CourseRepository courseRepository;
	
	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> getCourseList(){
		return courseRepository.findAllByDeleted(false);
	}
}
