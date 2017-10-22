package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.dto.ScheduleDto;
import edu.mum.se.poseidon.core.controllers.dto.ScheduleGenerateDto;
import edu.mum.se.poseidon.core.controllers.mapper.BlockMapper;
import edu.mum.se.poseidon.core.controllers.mapper.SectionMapper;
import edu.mum.se.poseidon.core.repositories.*;
import edu.mum.se.poseidon.core.repositories.models.*;
import edu.mum.se.poseidon.core.services.Schedule.BlockTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.*;

@Service
public class ScheduleService {

    private final int DEFAULT_MAX_SEAT = 25;
    private final int SCI_NUMBER = 500;
    private final int FPP_NUMBER = 390;
    private final int MPP_NUMBER = 401;

    private ScheduleRepository scheduleRepository;
    private EntryRepository entryRepository;
    private CourseRepository courseRepository;
    private FacultyRepository facultyRepository;
    private SectionRepository sectionRepository;
    private BlockRepository blockRepository;

    private SectionMapper sectionMapper;
    private BlockMapper blockMapper;

    @Autowired
    public ScheduleService(
            ScheduleRepository scheduleRepository,
            EntryRepository entryRepository,
            CourseRepository courseRepository,
            FacultyRepository facultyRepository,
            SectionRepository sectionRepository,
            BlockRepository blockRepository,
            SectionMapper sectionMapper,
            BlockMapper blockMapper) {
        this.scheduleRepository = scheduleRepository;
        this.entryRepository = entryRepository;
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
        this.sectionRepository = sectionRepository;
        this.blockRepository = blockRepository;
        this.sectionMapper = sectionMapper;
        this.blockMapper = blockMapper;
    }

    public Schedule getScheduleByEntryId(long entryId) {
        Entry entry = entryRepository.findOne(entryId);
        if (entry == null) {
            throw new RuntimeException("Entry not found");
        }
        return scheduleRepository.findByEntry(entry);
    }

    public Schedule getScheduleById(long scheduleId) {
        return scheduleRepository.findOne(scheduleId);
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAllByDeleted(false);
        //return scheduleRepository.findAll().stream().filter(x -> !x.getDeleted()).collect(Collectors.toList());
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
        schedule.setSections(dto.getSections()
                .stream()
                .map(x -> sectionMapper.getSectionFrom(x))
                .collect(Collectors.toList()));

        createSchedule(schedule);
        return schedule;
    }

    private void createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    /**
     * Updates Schedule's status
     * @param scheduleDto
     * @return Updated Schedule
     */
    public Schedule editSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleRepository.findOne(scheduleDto.getId());

        schedule.setStatus(scheduleDto.getStatus());

        // saves changes
        scheduleRepository.save(schedule);
        return schedule;
    }

    public Schedule generate(ScheduleGenerateDto dto) {

        Schedule schedule = new Schedule();

        Entry entry = entryRepository.findOne(dto.getEntryId());
        List<Course> courses = courseRepository.findAllByDeleted(false);
        List<Block> blocks = getSortedBlocks(entry);

        // Calculate Sections to Offer
        Map<Track, List<BlockTrack>> map = getBlockTracks(entry);

        // Create Section
        // Save Section
        // Assign Section
        List<Section> mppSections = assignSectionToBlock(map, Track.MPP, SCI_NUMBER);
//        List<Section> fppSections = assignSectionToBlock(map, Track.FPP, SCI_NUMBER);
        sectionRepository.save(mppSections);

        // findFaculty
        // addSection to Faculty
        // assignFaculty to Section
        // save Schedule
        schedule.setName(entry.getName() + " Schedule");
        schedule.setEntry(entry);
        scheduleRepository.save(schedule);
        // return schedule;
        return schedule;
    }

    private List<Section> assignSectionToBlock(Map<Track, List<BlockTrack>> map, Track track, int courseNumber) {
        BlockTrack b = map.get(track).get(0);
        Course sci = courseRepository.findCourseByNumber(courseNumber);
        List<Section> sections = new ArrayList();

        int s = getNSection(b.getnStudent());

        for (int i = 0; i < s; i++) {
            Section section = new Section();
            section.setMaxSeats(DEFAULT_MAX_SEAT);
            section.setCourse(sci);
            section.setLocation("Location-" + i);
            section.setBlock(b.getBlock());

            b.addSection(section);
            sections.add(section);
        }
        return sections;
    }

    private Map<Track, List<BlockTrack>> getBlockTracks(Entry entry) {
        Map<Track, List<BlockTrack>> map = new HashMap<>();
        map.put(Track.FPP, getFPPBlockTracks(entry));
        map.put(Track.MPP, getMPPBlockTracks(entry));
        return map;
    }

    private List<BlockTrack> getFPPBlockTracks(Entry entry) {
        int n = entry.getnFppOpt() + entry.getnFppStudents() + entry.getUsRes();
        return blockRepository.findAllByEntryAndDeleted(entry, false)
                .stream()
                .sorted(Comparator.comparing(x -> x.getStartDate()))
                .map(x -> new BlockTrack(x, n))
                .collect(Collectors.toList());
    }

    private List<BlockTrack> getMPPBlockTracks(Entry entry) {
        int n = entry.getnMppOpt() + entry.getnMppStudents() + entry.getUsRes();
        return blockRepository.findAllByEntryAndDeleted(entry, false)
                .stream()
                .sorted(Comparator.comparing(x -> x.getStartDate()))
                .map(x -> new BlockTrack(x, n))
                .collect(Collectors.toList());
    }

    private int getNSection(int nStudent) {
        if (nStudent % DEFAULT_MAX_SEAT == 0)
            return nStudent / DEFAULT_MAX_SEAT;
        return (nStudent / DEFAULT_MAX_SEAT) + 1;
    }

    private int getTotalStudents(Entry entry) {
        return entry.getnFppOpt() +
                entry.getnMppOpt() +
                entry.getnFppStudents() +
                entry.getUsRes() +
                entry.getnMppStudents();
    }

    private List<Block> getSortedBlocks(Entry entry) {
        return blockRepository.findAllByEntryAndDeleted(entry, false)
                .stream()
                .sorted(Comparator.comparing(x -> x.getStartDate()))
                .collect(Collectors.toList());
    }

    private List<BlockTrack> getSortedBlockTracks(Entry entry) {
        return blockRepository.findAllByEntryAndDeleted(entry, false)
                .stream()
                .sorted(Comparator.comparing(x -> x.getStartDate()))
                .map(x -> new BlockTrack(x, getTotalStudents(entry)))
                .collect(Collectors.toList());
    }
}

