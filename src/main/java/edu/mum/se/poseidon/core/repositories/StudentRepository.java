package edu.mum.se.poseidon.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.poseidon.core.repositories.models.users.Student;

public interface StudentRepository extends  JpaRepository<Student, Long>{

}
