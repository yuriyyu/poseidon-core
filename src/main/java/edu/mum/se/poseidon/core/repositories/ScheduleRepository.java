package edu.mum.se.poseidon.core.repositories;

import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.poseidon.core.repositories.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findByEntry(Entry entry);
}
