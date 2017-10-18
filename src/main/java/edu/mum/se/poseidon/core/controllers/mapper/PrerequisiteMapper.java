package edu.mum.se.poseidon.core.controllers.mapper;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.PrerequisiteDto;
import edu.mum.se.poseidon.core.repositories.models.Course;

@Component
public class PrerequisiteMapper {

	public PrerequisiteDto getPrerequisiteDto(Course course) {
		PrerequisiteDto dto = new PrerequisiteDto();
		dto.setId(course.getId());
		dto.setName(course.getName());
		dto.setNumber(course.getNumber());
		return dto;
	}
}
