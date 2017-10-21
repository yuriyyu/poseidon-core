package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            throw new PoseidonException("Entry is not found with this id.", HttpStatus.NOT_FOUND);
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
        Entry retVal = entryRepository.findOne(entry.getId());
        if (retVal == null) {
            throw new PoseidonException("Entry is not found with this id.", HttpStatus.NOT_FOUND);
        } else {
            retVal.setStartDate(entry.getStartDate());
            retVal.setnFppStudents(entry.getnFppStudents());
            retVal.setnMppStudents(entry.getnMppStudents());
            retVal.setnFppOpt(entry.getnFppOpt());
            retVal.setnMppOpt(entry.getnMppOpt());
            retVal.setUsRes(entry.getUsRes());
            retVal.setName(entry.getName());
            retVal.setBlockList(entry.getBlockList());
            retVal = entryRepository.save(entry);
            return retVal;
        }
    }
}
