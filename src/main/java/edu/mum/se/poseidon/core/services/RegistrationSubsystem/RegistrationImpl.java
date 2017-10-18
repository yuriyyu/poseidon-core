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

    @Autowired
    public RegistrationImpl(StudentRepository studentRepository, SectionRepository sectionRepository,
                            CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
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
        List<Section> retVal = new ArrayList<>();
        // Finding student's all section
        List<Section> sectionList = sectionRepository.findSectionsByDeleted(false);
        List<Section> studentPassedSectionList = sectionRepository.findSectionsPassedByStudentId(studentId);
        // checks that student passed course's pre-requisite
        // add sections that has no pre-requisites
        retVal.addAll(sectionList.stream()
                .filter(s -> courseRepository.findCourseBySectionId(s.getId()).getPrerequisites() == null
                        && courseRepository.findCourseBySectionId(s.getId()).getPrerequisites().isEmpty())
                .collect(Collectors.toList()));
        // add sections that has pre-requisites
        for (Section section : sectionList) {
            Course course = courseRepository.findCourseBySectionId(section.getId());
            List<Course> prerequisiteList = course.getPrerequisites();
            int count = 0;
            for (Section studentSection : studentPassedSectionList) {
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

    public List<Section> getSectionForRegister() {
        return null;
    }
}
