package edu.mum.se.poseidon.core.services.RegistrationSubsystem;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.*;
import edu.mum.se.poseidon.core.repositories.models.*;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationImpl implements IRegistration {

    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private CourseRepository courseRepository;

    @Autowired
    public RegistrationImpl(StudentRepository studentRepository,
                            SectionRepository sectionRepository,
                            CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void registerToSection(Long studentId, Long sectionId) throws PoseidonException {
        Section section = sectionRepository.findOne(sectionId);
        if (section == null) {
            throw new PoseidonException("Section is not found.", HttpStatus.NOT_FOUND);
        }
        Student student = studentRepository.findOne(studentId);
        if (student == null) {
            throw new PoseidonException("Student is not found.", HttpStatus.NOT_FOUND);
        }
        if (section.getStudentSections().size() > section.getMaxSeats()) {
            throw new PoseidonException("Seat is not available.", HttpStatus.BAD_REQUEST);
        }
        StudentSection ss = new StudentSection();
        StudentSectionPK sspk = new StudentSectionPK();
        sspk.setStudent(student);
        sspk.setSection(section);
        ss.setPassed(false);
        ss.setId(sspk);
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

    private List<Section> getSectionForRegister(List<Section> sectionList, List<Section> studentPassedSectionList) {
        List<Section> retVal = new ArrayList<>();
        for (Section section : sectionList) {
            Course course = courseRepository.findCourseBySectionId(section.getId());
            List<Course> prerequisiteList = course.getPrerequisites();
            if (prerequisiteList == null || prerequisiteList.isEmpty()) {
                retVal.add(section);
            } else {
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
        }
        return retVal;
    }
}
