package edu.mum.se.poseidon.core.services.RegistrationSubsystem;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.*;
import edu.mum.se.poseidon.core.repositories.models.*;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
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
    private FacultyRepository facultyRepository;

    @Autowired
    public RegistrationImpl(StudentRepository studentRepository,
                            SectionRepository sectionRepository,
                            CourseRepository courseRepository,
                            FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
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
        // Finding student's all section
        List<Section> sectionList = sectionRepository.findSectionsByDeleted(false);
        List<Section> studentPassedSectionList = sectionRepository.findSectionsPassedByStudentId(studentId);
        return getSectionForRegister(sectionList, studentPassedSectionList);
    }

    @Override
    public List<Section> getRegisteredSectionByStudent(Long studentId) {
        return sectionRepository.findSectionsByStudentId(studentId);
    }

    public List<Section> getSectionByFaculty(long facultyId) {
        Faculty faculty = facultyRepository.findOne(facultyId);
        if (faculty == null) {
            throw new RuntimeException("Faculty not found");
        }
        return sectionRepository.findByFaculty(faculty);
    }

    private List<Section> getSectionForRegister(List<Section> sectionList, List<Section> studentPassedSectionList) {
        List<Section> retVal = new ArrayList<>();
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
}
