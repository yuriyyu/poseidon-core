package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EntryService {

    private EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(LocalDate startDate, Integer nFppStudents, Integer nMppStudents, Integer nFppOpt,
                             Integer nMppOpt, Integer usRes, String name) {
        Entry entry = new Entry();
        entry.setStartDate(startDate);
        entry.setnFppStudents(nFppStudents);
        entry.setnMppStudents(nMppStudents);
        entry.setnFppOpt(nFppOpt);
        entry.setnMppOpt(nMppOpt);
        entry.setUsRes(usRes);
        entry.setName(name);
        entryRepository.save(entry);
        return entry;
    }
}
