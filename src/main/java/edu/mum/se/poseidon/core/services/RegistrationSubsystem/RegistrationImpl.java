package edu.mum.se.poseidon.core.services.RegistrationSubsystem;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.*;
import edu.mum.se.poseidon.core.repositories.models.*;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationImpl implements IRegistration {

    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private CourseRepository courseRepository;
    private BlockRepository blockRepository;
    private EntryRepository entryRepository;

    @Autowired
    public RegistrationImpl(StudentRepository studentRepository, SectionRepository sectionRepository,
                            CourseRepository courseRepository, BlockRepository blockRepository,
                            EntryRepository entryRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.blockRepository = blockRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public void registerToSection(Long studentId, Long sectionId) throws PoseidonException {
        Section section = sectionRepository.findOne(sectionId);
        if (section == null) {
            throw new PoseidonException("Section is not found with this Id.");
        }
        Student student = studentRepository.findOne(studentId);
        if (student == null) {
            throw new PoseidonException("Student is not found with this Id.");
        }
        StudentSection ss = new StudentSection();
        ss.setPassed(false);
        ss.getId().setSection(section);
        ss.getId().setStudent(student);
        section.getStudentSections().add(ss);
        student.getStudentSections().add(ss);
        studentRepository.save(student);
        sectionRepository.save(section);
    }

    @Override
    public List<Section> getAvailableSections(Long studentId) throws PoseidonException {
        final String nameOfCourseFPP = "FPP";
        final String nameOfCourseMPP = "MPP";
        final Long numberOfCourseFPPAndMPP = 2L;
        List<Section> retVal = new ArrayList<>();
        // Student must be completed MPP course
        List<Section> sectionsPassedByStudent = sectionRepository.findSectionsPassedByStudentId(studentId);
        Long totalNumber = sectionsPassedByStudent.stream()
                .map(s -> courseRepository.findCourseBySectionId(s.getId()))
                .filter(c -> c.getName().equals(nameOfCourseFPP))
                .filter(c -> c.getName().equals(nameOfCourseMPP))
                .count();
        if (!totalNumber.equals(numberOfCourseFPPAndMPP)) {
            throw new PoseidonException("Student must be completed 'MPP' course.");
        }
        // Finding student's entry
        Student student = studentRepository.findOne(studentId);
        Entry studentEntry = entryRepository.findEntriesByDeleted(false)
                .stream()
                .filter(e -> e.getStudentList().contains(student))
                .findFirst().get();
        // Finding student's elective blocks
        List<Block> studentBlockList = blockRepository.findAll()
                .stream()
                .filter(b -> !b.getDeleted())
                .filter(b -> b.getEntry() == studentEntry)
                .collect(Collectors.toList());
        // Finding student's all section
        List<Section> availableSectionList = sectionRepository.findAll()
                .stream()
                .filter(s -> !s.getDeleted())
                .filter(s -> studentBlockList.contains(s.getBlock()))
                .collect(Collectors.toList());
        // checks that student passed course's pre-requisite
        // add sections that has no pre-requisites
        retVal.addAll(availableSectionList.stream()
                .filter(s -> courseRepository.findCourseBySectionId(s.getId()).getPrerequisites() == null
                        && courseRepository.findCourseBySectionId(s.getId()).getPrerequisites().isEmpty())
                .collect(Collectors.toList()));
        // add sections that has pre-requisites
        for (Section section : availableSectionList) {
            Course course = courseRepository.findCourseBySectionId(section.getId());
            List<Course> prerequisiteList = course.getPrerequisites();
            int count = 0;
            for (Section studentSection : sectionsPassedByStudent) {
                Course studentCourse = courseRepository.findCourseBySectionId(studentSection.getId());
                if (prerequisiteList.contains(studentCourse)) {
                    count++;
                }
            }
            if (count == prerequisiteList.size()) {
                retVal.add(section);
            }
        }
        return retVal;
    }

    @Override
    public List<Section> getRegisteredSectionByStudent(Long studentId) {
        return sectionRepository.findSectionsByStudentId(studentId);
    }
}
