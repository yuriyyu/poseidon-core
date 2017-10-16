package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    Entry findEntryByStartDate(LocalDate startDate);

    List<Entry> findEntriesByDeleted(Boolean isDeleted);

}
