package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.dto.ScheduleDto;
import edu.mum.se.poseidon.core.controllers.dto.ScheduleGenerateDto;
import edu.mum.se.poseidon.core.controllers.mapper.BlockMapper;
import edu.mum.se.poseidon.core.controllers.mapper.SectionMapper;
import edu.mum.se.poseidon.core.repositories.*;
import edu.mum.se.poseidon.core.repositories.models.*;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import edu.mum.se.poseidon.core.services.Schedule.BlockTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.*;

@Service
public class ScheduleService {

    private final int DEFAULT_MAX_SEAT = 25;
    private final int SCI_NUMBER = 500;
    private final int FPP_NUMBER = 390;
    private final int MPP_NUMBER = 401;
    private final String DEFAULT_FACULTY = "UNSTAFF";

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
     *
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

    /**
     * Generates Schedule
     * Assumes we have at least 2 blocks to cover SCI and MPP
     *
     * @param dto ScheduleGenerateDto instance
     * @return Created Schedule instance.
     */
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
        List<Section> mppSections = new ArrayList<>();
        List<Section> fppSections = new ArrayList<>();

        // SCI and FPP - MPP
        BlockTrack sciBlock = map.get(Track.MPP).get(0);
        BlockTrack mpp = map.get(Track.MPP).get(1);

//        for (int i = 0; i < getNSection(sciBlock.getnStudent()); i++) {
//            mppSections.addAll(assignSectionToBlock(sciBlock.getBlock(), SCI_NUMBER));
//            mppSections.addAll(assignSectionToBlock(mpp.getBlock(), MPP_NUMBER));
//        }

        mppSections.addAll(assignSectionToBlock(sciBlock, SCI_NUMBER));
        mppSections.addAll(assignSectionToBlock(mpp, MPP_NUMBER));

        // Elective Block
        for (BlockTrack b : map.get(Track.MPP).stream().skip(2).collect(Collectors.toList())) {
            List<Integer> numbers = findElectiveForBlock(b.getnStudent());
            for (int i = 0; i < numbers.size(); i++) {
                mppSections.addAll(assignSectionToBlock(b.getBlock(), numbers.get(i)));
            }
        }

        // findFaculty
        // addSection to Faculty
        // assignFaculty to Section

        assignFacultyToSection(mppSections);

        // save Schedule
        schedule.setName(entry.getName() + " Schedule");
        schedule.setEntry(entry);
        schedule.setSections(mppSections);
        scheduleRepository.save(schedule);

        return schedule;
    }

    private void assignFacultyToSection(List<Section> mppSections) {
        for (Section section : mppSections) {
            //Course c = section.getCourse();
            Course c = courseRepository.findCourseBySectionId(section.getId());

            // Check if the course has assigned-faculty
            if (c != null) {
                if (c.getFaculties() != null && c.getFaculties().size() != 0) {
                    int s = c.getFaculties().size();
                    Faculty f = c.getFaculties().stream().collect(Collectors.toList()).get(getRandomIndex(s));
                    section.setFaculty(f);
                } else { // Find Faculty that specialised in Selected Course.
                    List<Faculty> faculties = facultyRepository.findFacultiesByCourseId(c.getId());
                    if (faculties != null && faculties.size() != 0) {
                        int s = faculties.size();
                        section.setFaculty(faculties.get(getRandomIndex(s)));
                    }
                }
                sectionRepository.save(section);
            } else {
                System.out.println("Section Id: " + section.getId());
                System.out.println("COURSE IS NULL---");
                if (section.getCourse() != null)
                    System.out.println(section.getCourse().getName());
            }
        }
    }

    private int getRandomIndex(int upperBound) {
        Random random = new Random();
        int lowerBound = 0;
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    private List<Section> assignSectionToBlock(Block block, int courseNumber) {

        Course course = courseRepository.findCourseByNumber(courseNumber);
        Faculty unstaffed = facultyRepository.findByFirstNameEquals(DEFAULT_FACULTY);

        List<Section> sections = new ArrayList<>();
        Section section = new Section();
        section.setMaxSeats(DEFAULT_MAX_SEAT);
        section.setCourse(course);
        section.setLocation("UNASSIGNED");
        section.setBlock(block);

        // set to unstaffed
        section.setFaculty(unstaffed);
        sections.add(section);

        // saves Sections
        sectionRepository.save(section);

        // saves block
        block.setSectionList(sections);
        blockRepository.save(block);
        return sections;
    }

    // TODO: Degree of freedom
    private List<Integer> findElectiveForBlock(int nStudent) {
        int s = getNSection(nStudent);
        List<Integer> c = courseRepository
                .findAllByDeleted(false)
                .parallelStream()
                .filter(x -> x.getNumber() != SCI_NUMBER && x.getNumber() != MPP_NUMBER && x.getNumber() != FPP_NUMBER)
                .map(x -> x.getNumber())
                .collect(Collectors.toList());

        // Randomizes the list.
        return randomizeList(c, s);
    }

    /**
     * Randomizes the list
     *
     * @param list Collection to Random
     * @param size Number of element to take
     * @return Random list of size.
     */
    private List<Integer> randomizeList(List<Integer> list, int size) {
        List<Integer> r = new ArrayList<>();
        while (r.size() < size) {
            int idx = getRandomIndex(list.size());
            int f = list.get(idx); // Gets CourseNumber
            if (!isInList(r, f))
                r.add(f);
        }
        return r;
    }

    private boolean isInList(List<Integer> l, int f) {
        for (Integer i : l) {
            if (i == f)
                return true;
        }
        return false;
    }

    private List<Section> assignSectionToBlock(BlockTrack b, int courseNumber) {
        Course sci = courseRepository.findCourseByNumber(courseNumber);
        Faculty unstaffed = facultyRepository.findByFirstNameEquals(DEFAULT_FACULTY);
        List<Section> sections = new ArrayList();
        int s = getNSection(b.getnStudent());
        Block block = b.getBlock();

        for (int i = 0; i < s; i++) {
            Section section = new Section();
            section.setMaxSeats(DEFAULT_MAX_SEAT);
            section.setCourse(sci);
            section.setLocation("Location-" + i);
            section.setBlock(b.getBlock());

            // set to unstaffed
            section.setFaculty(unstaffed);
            b.addSection(section);
            sections.add(section);

            // save sections
            sectionRepository.save(section);
        }

        block.setSectionList(sections);
        blockRepository.save(block);

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

