package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.dto.ScheduleDto;
import edu.mum.se.poseidon.core.controllers.dto.ScheduleGenerateDto;
import edu.mum.se.poseidon.core.repositories.*;
import edu.mum.se.poseidon.core.repositories.models.Block;
import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import edu.mum.se.poseidon.core.repositories.models.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.*;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private EntryRepository entryRepository;
    private CourseRepository courseRepository;
    private FacultyRepository facultyRepository;
    private SectionRepository sectionRepository;
    private BlockRepository blockRepository;

    @Autowired
    public ScheduleService(
            ScheduleRepository scheduleRepository,
            EntryRepository entryRepository,
            CourseRepository courseRepository,
            FacultyRepository facultyRepository,
            SectionRepository sectionRepository,
            BlockRepository blockRepository) {
        this.scheduleRepository = scheduleRepository;
        this.entryRepository = entryRepository;
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
        this.sectionRepository = sectionRepository;
        this.blockRepository = blockRepository;
    }

    public Schedule getSchedule(long entryId) {
        Entry entry = entryRepository.findOne(entryId);
        if (entry == null) {
            throw new RuntimeException("Entry not found");
        }
        return scheduleRepository.findByEntry(entry);
    }

    public List<Schedule> getSchedules() {

        return scheduleRepository.findAll().stream().filter(x -> !x.getDeleted()).collect(Collectors.toList());
    }

    public void deleteSchedule(long scheduleId) {
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        schedule.setDeleted(true);
        scheduleRepository.save(schedule);
    }

    public Schedule createSchedule(ScheduleDto dto) {

        Schedule schedule = new Schedule();
        schedule.setDcreated(now());
        schedule.setName(dto.getName());
        schedule.setStatus(dto.getStatus());
        schedule.setSections(dto.getSections());

        createSchedule(schedule);
        return schedule;
    }

    private void createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public Schedule editSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleRepository.findOne(scheduleDto.getId());
        schedule.setStatus(scheduleDto.getStatus());
        schedule.setName(scheduleDto.getName());

        // saves changes
        scheduleRepository.save(schedule);
        return schedule;
    }

    public Schedule generate(ScheduleGenerateDto dto) {
        Schedule schedule = new Schedule();
        Entry entry = entryRepository.findOne(dto.getEntryId());
        List<Course> courses = courseRepository.findAllByDeleted(false);
        List<Block> blocks = blockRepository.findAllByEntryAndDeleted(entry, false); // includes DELETE

        // Calculate Sections to Offer
        // Create Section
        // Save Section
        // Assign Section
        // findFaculty
        // addSection to Faculty
        // assignFaculty to Section
        // save Schedule
        return schedule;
    }
}
