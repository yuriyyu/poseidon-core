package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.FacultyRepository;
import edu.mum.se.poseidon.core.repositories.SectionRepository;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private SectionRepository sectionRepository;
    private FacultyRepository facultyRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository, FacultyRepository facultyRepository) {
        this.sectionRepository = sectionRepository;
        this.facultyRepository = facultyRepository;
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public void deleteSection(Long id) throws PoseidonException {
        Section section = sectionRepository.getOne(id);
        if (section == null) {
            throw new PoseidonException("Section is not found with this id.", HttpStatus.NOT_FOUND);
        } else {
            section.setDeleted(true);
            sectionRepository.save(section);
        }
    }

    public Section editSection(Section section) throws PoseidonException {
        Section retVal = sectionRepository.getOne(section.getId());
        if (retVal == null) {
            throw new PoseidonException("Section is not found with this id.", HttpStatus.NOT_FOUND);
        } else {
            retVal.setLocation(section.getLocation());
            retVal.setMaxSeats(section.getMaxSeats());
            retVal = sectionRepository.save(retVal);
            return retVal;
        }
    }

    public List<Section> getSectionList() {
        return sectionRepository.findSectionsByDeleted(false);
    }

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

    public Section getSection(Long id) {
        return sectionRepository.findOne(id);
    }
}
