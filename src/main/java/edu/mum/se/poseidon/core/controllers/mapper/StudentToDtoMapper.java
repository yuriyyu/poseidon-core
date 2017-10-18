package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.StudentProfileDto;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentToDtoMapper {

    public StudentToDtoMapper() {

    }

    public StudentProfileDto getStudentDtoFrom(Student student) {
        if(student == null)  {
            return null;
        }

        StudentProfileDto dto = new StudentProfileDto();
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setUsername(student.getUsername());
        dto.setPassword(student.getPassword());

        return dto;
    }
}
