package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.SectionRepository;
import edu.mum.se.poseidon.core.repositories.models.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public void deleteSection(Long id) throws PoseidonException {
        Section section = sectionRepository.getOne(id);
        if (section == null) {
            throw new PoseidonException("Section is not found with this id.");
        } else {
            section.setDeleted(true);
            sectionRepository.save(section);
        }
    }

    public Section editSection(Section section) throws PoseidonException {
        Section section1 = sectionRepository.getOne(section.getId());
        if (section1 == null) {
            throw new PoseidonException("Section is not found with this id.");
        } else {
            section1.setLocation(section.getLocation());
            section1.setMaxSeats(section.getMaxSeats());
            sectionRepository.save(section1);
            return section1;
        }
    }

    public List<Section> getSectionList() {
        return sectionRepository.findSectionsByDeleted(false);
    }

    public Section getSection(Long id) {
        return sectionRepository.findOne(id);
    }
}
