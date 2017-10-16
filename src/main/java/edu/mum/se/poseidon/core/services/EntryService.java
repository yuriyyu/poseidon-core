package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.controllers.PoseidonException;
import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {

    private EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) throws PoseidonException {
        if (entryRepository.findByStartDate(entry.getStartDate()) == null) {
            entryRepository.save(entry);
            return entry;
        } else {
            throw new PoseidonException("Entry is already created this with this startDate.");
        }
    }

    public Entry deleteEntry(Long id) throws PoseidonException {
        Entry entry = entryRepository.getOne(id);
        if (entry == null) {
            throw new PoseidonException("Entry is not found with this id.");
        } else {
            entryRepository.delete(entry);
            return entry;
        }
    }

    public List<Entry> readEntryList() {
        return entryRepository.findAll();
    }

    public Entry readEntry(Long id) {
        return entryRepository.findOne(id);
    }

    public Entry updateEntry(Entry entry) throws PoseidonException {
        if (entryRepository.exists(entry.getId())) {
            entryRepository.save(entry);
            return entry;
        } else {
            throw new PoseidonException("Entry is not found with this id.");
        }
    }
}
