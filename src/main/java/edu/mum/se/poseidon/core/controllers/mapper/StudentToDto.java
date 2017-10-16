package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.StudentDto;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentToDto {

    public StudentToDto() {

    }

    public StudentDto getStudentDtoFrom(Student student) {
        if(student == null)  {
            return null;
        }

        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setUsername(student.getUsername());
        dto.setPassword(student.getPassword());

        return dto;
    }
}
