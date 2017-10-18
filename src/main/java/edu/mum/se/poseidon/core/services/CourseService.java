package edu.mum.se.poseidon.core.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.controllers.mapper.FacultyMapper;
import edu.mum.se.poseidon.core.controllers.mapper.PrerequisiteMapper;
import edu.mum.se.poseidon.core.repositories.CourseRepository;
import edu.mum.se.poseidon.core.repositories.models.Course;

@Service
public class CourseService {

	private CourseRepository courseRepository;
	private PrerequisiteMapper prerequisiteMapper;
	private FacultyMapper facultyMapper;
	
	@Autowired
	public CourseService(CourseRepository courseRepository, PrerequisiteMapper prerequisiteMapper,
			FacultyMapper facultyMapper) {
		this.courseRepository = courseRepository;
		this.prerequisiteMapper = prerequisiteMapper;
		this.facultyMapper = facultyMapper;
	}
	
	public Course createCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setName(courseDto.getName());
		course.setNumber(courseDto.getNumber());
		course.setFaculties(courseDto.getFaculties()
				.stream()
				.map(f -> facultyMapper.getFaculty(f))
				.collect(Collectors.toList()));
		course.setPrerequisites(courseDto.getPrerequisites().stream()
				.map(pre -> prerequisiteMapper.getCourse(pre))
				.collect(Collectors.toList()));
		course = courseRepository.save(course);
		return course;
	}
	
	public Course editCourse(CourseDto courseDto) {
		Course course = courseRepository.findOne(courseDto.getId());
		course.setName(courseDto.getName());
		course.setNumber(courseDto.getNumber());
		course.setFaculties(courseDto.getFaculties()
				.stream()
				.map(f -> facultyMapper.getFaculty(f))
				.collect(Collectors.toList()));
		course.setPrerequisites(courseDto.getPrerequisites().stream()
				.map(pre -> prerequisiteMapper.getCourse(pre))
				.collect(Collectors.toList()));
		course = courseRepository.save(course);
		return course;
	}
	
	public List<Course> getCourseList(){
		return courseRepository.findAllByDeleted(false);
	}
	
	public Course getCourse(long courseId) {
    	return courseRepository.findOne(courseId);
    }
    
    public void deleteCourse(long courseId) {
    	Course course = courseRepository.findOne(courseId);
    	course.setDeleted(true);
    	courseRepository.save(course);
    }
}
