package edu.mum.se.poseidon.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.poseidon.core.repositories.models.Block;

public interface BlockRepository extends JpaRepository<Block, Long>{

	List<Block> findAllByDeleted(boolean is_deleted);
	
}
