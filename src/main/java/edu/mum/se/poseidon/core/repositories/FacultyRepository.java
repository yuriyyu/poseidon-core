package edu.mum.se.poseidon.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.poseidon.core.repositories.models.users.Faculty;

public interface FacultyRepository extends  JpaRepository<Faculty, Long>{

	List<Faculty> findAllByDeleted(boolean is_deleted);

	Faculty findByFirstNameEquals(String firstname);
}
