package edu.mum.se.poseidon.core.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.mum.se.poseidon.core.controllers.dto.FacultyProfileDto;
import edu.mum.se.poseidon.core.repositories.CourseRepository;
import edu.mum.se.poseidon.core.repositories.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyService {

	private FacultyRepository facultyRepository;
	private CourseRepository courseRepository;
	
	@Autowired
	public FacultyService(FacultyRepository facultyRepository,
                          CourseRepository courseRepository) {
		this.facultyRepository = facultyRepository;
		this.courseRepository = courseRepository;
	}
	
	public List<Faculty> getFacultyList() {
		return facultyRepository.findAllByDeleted(false);
	}

	public Faculty getFaculty(long id) {
		return facultyRepository.findOne(id);
	}

    @Transactional
    public Faculty updateProfile(long id, FacultyProfileDto profileDto) {
        Faculty faculty = getFaculty(id);
        if(faculty == null) {
            throw new RuntimeException("User not found");
        }

        Set<Long> courseIds = profileDto.getCourseList().stream()
                .map(courseInfoDto -> courseInfoDto.getId())
                .collect(Collectors.toSet());
        Set<Course> courses = courseRepository.findByIdIn(courseIds);

        faculty.setFirstName(profileDto.getFirstName());
        faculty.setLastName(profileDto.getLastName());
        faculty.setUsername(profileDto.getUsername());
        faculty.setPassword(profileDto.getPassword());
        faculty.setCourses(courses);

        faculty = facultyRepository.save(faculty);

        return faculty;
    }
}
