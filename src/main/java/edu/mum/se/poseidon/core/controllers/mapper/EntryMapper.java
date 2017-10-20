package edu.mum.se.poseidon.core.controllers.mapper;

import edu.mum.se.poseidon.core.configs.Helper;
import edu.mum.se.poseidon.core.controllers.dto.EntryDto;
import edu.mum.se.poseidon.core.repositories.models.Entry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntryMapper {

    public EntryDto getEntryDtoFrom(Entry entry) {
        if (entry == null) {
            return null;
        }
        EntryDto entryDto = new EntryDto();
        entryDto.setId(entry.getId());
        entryDto.setnFppOpt(entry.getnFppOpt());
        entryDto.setnMppOpt(entry.getnMppOpt());
        entryDto.setnMppStudents(entry.getnMppStudents());
        entryDto.setnFppStudents(entry.getnFppStudents());
        entryDto.setStartDate(Helper.convertDateToString(entry.getStartDate()));
        entryDto.setUsRes(entry.getUsRes());
        entryDto.setName(entry.getName());
        return entryDto;
    }

    public Entry getEntryFrom(EntryDto entryDto) {
        if (entryDto == null) {
            return null;
        }
        Entry entry = new Entry();
        entry.setId(entryDto.getId());
        entry.setnFppOpt(entryDto.getnFppOpt());
        entry.setnMppOpt(entryDto.getnMppOpt());
        entry.setnMppStudents(entryDto.getnMppStudents());
        entry.setnFppStudents(entryDto.getnFppStudents());
        entry.setStartDate(Helper.convertStringToDate(entryDto.getStartDate()));
        entry.setUsRes(entryDto.getUsRes());
        entry.setName(entryDto.getName());
        return entry;
    }

    public List<EntryDto> getEntryDtoListFrom(List<Entry> entryList) {
        if (entryList == null) {
            return new ArrayList<>();
        }
        return entryList.stream().map(e -> getEntryDtoFrom(e)).collect(Collectors.toList());
    }
}
