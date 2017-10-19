package edu.mum.se.poseidon.core.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyDto;
import edu.mum.se.poseidon.core.controllers.mapper.FacultyMapper;
import edu.mum.se.poseidon.core.controllers.mapper.PrerequisiteMapper;
import edu.mum.se.poseidon.core.repositories.CourseRepository;
import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

@Service
public class CourseService {

	private CourseRepository courseRepository;
	private FacultyRepository facultyRepository;
	private PrerequisiteMapper prerequisiteMapper;
	private FacultyMapper facultyMapper;
	
	@Autowired
	public CourseService(CourseRepository courseRepository, FacultyRepository facultyRepository,
			PrerequisiteMapper prerequisiteMapper, FacultyMapper facultyMapper) {
		this.courseRepository = courseRepository;
		this.facultyRepository = facultyRepository;
		this.prerequisiteMapper = prerequisiteMapper;
		this.facultyMapper = facultyMapper;
	}
	
	public Course createCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setName(courseDto.getName());
		course.setNumber(courseDto.getNumber());
		course.setPrerequisites(courseDto.getPrerequisites().stream()
				.map(pre -> prerequisiteMapper.getCourse(pre))
				.collect(Collectors.toList()));
		course = courseRepository.save(course);
		for(FacultyDto dto: courseDto.getFaculties()) {
			Faculty faculty = facultyRepository.findOne(dto.getId());
			if(!faculty.getCourses().contains(course)) {
				faculty.addCourse(course);
			}
			facultyRepository.save(faculty);
		}
		return course;
	}
	
	public Course editCourse(CourseDto courseDto) {
		Course course = courseRepository.findOne(courseDto.getId());
		course.setName(courseDto.getName());
		course.setNumber(courseDto.getNumber());
		course.setPrerequisites(courseDto.getPrerequisites().stream()
				.map(pre -> prerequisiteMapper.getCourse(pre))
				.collect(Collectors.toList()));
		course = courseRepository.save(course);
		for(FacultyDto dto: courseDto.getFaculties()) {
			Faculty faculty = facultyRepository.findOne(dto.getId());
			if(!faculty.getCourses().contains(course)) {
				faculty.addCourse(course);
			}
			facultyRepository.save(faculty);
		}
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
