package edu.mum.se.poseidon.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

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
}
