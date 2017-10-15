package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.EntryDto;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper {

    public EntryDto getEntryDtoFrom(Entry entry) {
        EntryDto entryDto = new EntryDto();
        entryDto.setnFppOpt(entry.getnFppOpt());
        entryDto.setnMppOpt(entry.getnMppOpt());
        entryDto.setnMppStudents(entry.getnMppStudents());
        entryDto.setnFppStudents(entry.getnFppStudents());
        entryDto.setStartDate(entry.getStartDate());
        entryDto.setUsRes(entry.getUsRes());
        entryDto.setName(entry.getName());
        return entryDto;
    }

    public Entry getEntryFrom(EntryDto entryDto) {
        Entry entry = new Entry();
        entry.setnFppOpt(entryDto.getnFppOpt());
        entry.setnMppOpt(entryDto.getnMppOpt());
        entry.setnMppStudents(entryDto.getnMppStudents());
        entry.setnFppStudents(entryDto.getnFppStudents());
        entry.setStartDate(entryDto.getStartDate());
        entry.setUsRes(entryDto.getUsRes());
        entry.setName(entryDto.getName());
        return entry;
    }
}
