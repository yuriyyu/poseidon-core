package edu.mum.se.poseidon.core.services.RegistrationSubsystem;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.CourseRepository;
import edu.mum.se.poseidon.core.repositories.SectionRepository;
import edu.mum.se.poseidon.core.repositories.StudentRepository;
import edu.mum.se.poseidon.core.repositories.models.Course;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationImp implements IRegistration {

    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private CourseRepository courseRepository;

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
        section.getStudentList().add(student);
        student.getSectionList().add(section);
        studentRepository.save(student);
        sectionRepository.save(section);
    }

    @Override
    public List<Section> getAvailableSections(Long studentId) throws PoseidonException {
        final String nameOfCourseFPP = "FPP";
        final String nameOfCourseMPP = "MPP";
        final Long numberOfCourseFPPAndMPP = 2L;
        List<Section> retVal = new ArrayList<>();
        List<Section> sectionList = sectionRepository.findAll();
        List<Section> studentSectionList = sectionRepository.findSectionsByStudentId(studentId);
        Long totalNumber = studentSectionList.stream()
                .map(s -> courseRepository.findCourseBySectionId(s.getId()))
                .filter(c -> c.getName().equals(nameOfCourseFPP))
                .filter(c -> c.getName().equals(nameOfCourseMPP))
                .count();
        if (!totalNumber.equals(numberOfCourseFPPAndMPP)) {
            throw new PoseidonException("Student must be completed 'MPP' course.");
        }
        // checks that student studied course's pre-requisite
        for (Section section : sectionList) {
            Course course = courseRepository.findCourseBySectionId(section.getId());
            List<Course> prerequisiteList = course.getPrerequisites();
            if (prerequisiteList == null || prerequisiteList.isEmpty()) {
                retVal.add(section);
                continue;
            }
            for (Section studentSection : studentSectionList) {
                Course course1 = courseRepository.findCourseBySectionId(section.getId());
                if (prerequisiteList.contains(course1)) {
                    retVal.add(section);
                    break;
                }
            }
        }
        return retVal;
    }

    @Override
    public List<Section> getRegisteredSectionByStudent(Long studentId) {
        return sectionRepository.findSectionsByStudentId(studentId);
    }
}
