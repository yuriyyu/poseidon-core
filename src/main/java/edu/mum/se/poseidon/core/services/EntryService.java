package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntryService {

    private EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) throws PoseidonException {
        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id) throws PoseidonException {
        Entry entry = entryRepository.findOne(id);
        if (entry == null) {
            throw new PoseidonException("Entry is not found with this id.");
        } else {
            entry.setDeleted(true);
            entryRepository.save(entry);
        }
    }

    public List<Entry> getEntryList() {
        return entryRepository.findEntriesByDeleted(false);
    }

    public Entry getEntryByStartDate(LocalDate startDate) {
        return entryRepository.findEntryByStartDate(startDate);
    }

    public Entry getEntry(Long id) {
        return entryRepository.findOne(id);
    }

    public Entry editEntry(Entry entry) throws PoseidonException {
        Entry entry1 = entryRepository.findOne(entry.getId());
        if (entry1 == null) {
            throw new PoseidonException("Entry is not found with this id.");
        } else {
            entry1.setStartDate(entry.getStartDate());
            entry1.setnFppStudents(entry.getnFppStudents());
            entry1.setnMppStudents(entry.getnMppStudents());
            entry1.setnFppOpt(entry.getnFppOpt());
            entry1.setnMppOpt(entry.getnMppOpt());
            entry1.setUsRes(entry.getUsRes());
            entry1.setName(entry.getName());
            entry1.setBlockList(entry.getBlockList());
            entryRepository.save(entry);
            return entry1;
        }
    }
}
