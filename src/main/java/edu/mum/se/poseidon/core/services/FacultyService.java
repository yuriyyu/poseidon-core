package edu.mum.se.poseidon.core.services;

import java.util.List;

import edu.mum.se.poseidon.core.controllers.dto.FacultyProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyService {

	private FacultyRepository facultyRepository;
	
	@Autowired
	public FacultyService(FacultyRepository facultyRepository) {
		this.facultyRepository = facultyRepository;
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

        faculty.setFirstName(profileDto.getFirstName());
        faculty.setLastName(profileDto.getLastName());
        faculty.setUsername(profileDto.getUsername());
        faculty.setPassword(profileDto.getPassword());

        faculty = facultyRepository.save(faculty);

        return faculty;
    }
}
