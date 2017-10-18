package edu.mum.se.poseidon.core.controllers.mapper;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.FacultyDto;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

@Component
public class FacultyMapper {

	public FacultyDto getFacultyDto(Faculty faculty) {
		FacultyDto facultyDto = new FacultyDto();
		facultyDto.setId(faculty.getId());
		facultyDto.setFirstName(faculty.getFirstName());
		facultyDto.setLastName(faculty.getLastName());
		return facultyDto;
	}
}
