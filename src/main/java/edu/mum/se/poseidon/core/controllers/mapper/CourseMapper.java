package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.CourseDto;
import edu.mum.se.poseidon.core.controllers.dto.CourseInfoDto;
import edu.mum.se.poseidon.core.repositories.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

	private FacultyMapper facultyMapper;
	private PrerequisiteMapper prerequisiteMapper;
	
	@Autowired
	public CourseMapper(FacultyMapper facultyMapper, PrerequisiteMapper prerequisiteMapper) {
		this.facultyMapper = facultyMapper;
		this.prerequisiteMapper = prerequisiteMapper;
	}
	
	public CourseDto getCourseDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setName(course.getName());
		courseDto.setNumber(course.getNumber());
		
		courseDto.setPrerequisites(course.getPrerequisites()
					.stream()
					.map(p -> prerequisiteMapper.getPrerequisiteDto(p))
					.collect(Collectors.toList()));
		
		courseDto.setFaculties(course.getFaculties()
					.stream()
					.map(f -> facultyMapper.getFacultyDto(f))
					.collect(Collectors.toList()));
		return courseDto;
	}

    public List<CourseInfoDto> getCourseInfoDtoList(Set<Course> courses) {
        if(courses == null) {
            return null;
        }

        return courses.stream()
                .map(c -> getCourseInfoDto(c))
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }
    
    public Course getCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setId(courseDto.getId());
		course.setName(courseDto.getName());
		course.setNumber(courseDto.getNumber());
		
		course.setPrerequisites(courseDto.getPrerequisites()
					.stream()
					.map(p -> prerequisiteMapper.getCourse(p))
					.collect(Collectors.toList()));
		course.setFaculties(courseDto.getFaculties()
					.stream()
					.map(f -> facultyMapper.getFaculty(f))
					.collect(Collectors.toSet()));
		return course;
	}

    public CourseInfoDto getCourseInfoDto(Course course) {
	    if(course == null) {
	        return null;
        }

        CourseInfoDto dto = new CourseInfoDto();
	    dto.setId(course.getId());
	    dto.setName(course.getName());
	    dto.setNumber(course.getNumber());

	    return dto;
    }
}
