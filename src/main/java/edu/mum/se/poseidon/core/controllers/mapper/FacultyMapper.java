package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.CourseInfoDto;
import edu.mum.se.poseidon.core.controllers.dto.FacultyProfileDto;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

import java.util.List;

@Component
public class FacultyMapper {

	public FacultyProfileDto getFacultyProfileDtoFrom(Faculty faculty,
                                                      List<CourseInfoDto> courseInfoDtos) {
		if(faculty == null) {
			return null;
		}

		FacultyProfileDto dto = new FacultyProfileDto();
		dto.setId(faculty.getId());
		dto.setFirstName(faculty.getFirstName());
		dto.setLastName(faculty.getLastName());
		dto.setUsername(faculty.getUsername());
		dto.setPassword(faculty.getPassword());
		dto.setCourseList(courseInfoDtos);

		dto.setType(faculty.getType());
		return dto;
	}
	
	public Faculty getFaculty(FacultyProfileDto facultyDto) {
		Faculty faculty = new Faculty();
		faculty.setId(facultyDto.getId());
		faculty.setFirstName(facultyDto.getFirstName());
		faculty.setLastName(facultyDto.getLastName());
		faculty.setUsername(facultyDto.getUsername());
		faculty.setPassword(facultyDto.getPassword());
		faculty.setType(facultyDto.getType());
		return faculty;
	}
}
