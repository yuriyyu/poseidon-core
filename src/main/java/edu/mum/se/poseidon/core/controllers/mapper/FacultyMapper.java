package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.FacultyProfileDto;
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

	public FacultyProfileDto getFacultyProfileDtoFrom(Faculty faculty) {
		if(faculty == null) {
			return null;
		}

		FacultyProfileDto dto = new FacultyProfileDto();
		dto.setFirstName(faculty.getFirstName());
		dto.setLastName(faculty.getLastName());
		dto.setUsername(faculty.getUsername());
		dto.setPassword(faculty.getPassword());

		return dto;
	}
}
