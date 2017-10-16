package edu.mum.se.poseidon.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.se.poseidon.core.repositories.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
