package edu.mum.se.poseidon.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.ScheduleRepository;

@Service
public class ScheduleService {
	
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	public ScheduleService(ScheduleRepository repo) {
		this.scheduleRepository = repo;
	}
}
