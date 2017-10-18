package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.repositories.ScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        if (entry == null) {
            throw new RuntimeException("Entry not found");
        }
        Schedule schedule = scheduleRepository.findByEntry(entry);

        return schedule;
    }

    public List<Schedule> getSchedules() {

        return scheduleRepository.findAll().stream().filter(x -> !x.getDeleted()).collect(Collectors.toList());
    }

    public void deleteSchedule(long scheduleId) {
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        schedule.setDeleted(true);
        scheduleRepository.save(schedule);
    }


}
