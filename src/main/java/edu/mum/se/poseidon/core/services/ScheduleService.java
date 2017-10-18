package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.ScheduleRepository;

@Service
public class ScheduleService {
	
	private ScheduleRepository scheduleRepository;
    private EntryRepository entryRepository;

	@Autowired
	public ScheduleService(ScheduleRepository repo, EntryRepository entryRepository) {
		this.scheduleRepository = repo;
		this.entryRepository = entryRepository;
	}

	public Schedule getSchedule(long entryId) {
        Entry entry = entryRepository.findOne(entryId);
        if(entry == null) {
            throw new RuntimeException("Entry not found");
        }
	    Schedule schedule = scheduleRepository.findByEntry(entry);

        return schedule;
    }
}
