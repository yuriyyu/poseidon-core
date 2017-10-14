package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.controllers.dto.EntryDto;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.stereotype.Component;

@Component
public class EntryToDto {

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
}
