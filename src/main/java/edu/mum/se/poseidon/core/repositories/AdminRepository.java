package edu.mum.se.poseidon.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.poseidon.core.repositories.models.users.Admin;

public interface AdminRepository extends  JpaRepository<Admin, Long>{

}
