package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    Entry findByStartDate(LocalDate startDate);

}
