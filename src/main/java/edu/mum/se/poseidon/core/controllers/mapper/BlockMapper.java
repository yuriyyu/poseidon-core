package edu.mum.se.poseidon.core.controllers.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.se.poseidon.core.controllers.dto.BlockTrackDto;
import edu.mum.se.poseidon.core.repositories.models.Section;
import edu.mum.se.poseidon.core.services.Schedule.BlockTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.repositories.models.Block;

@Component
public class BlockMapper {

    private EntryMapper entryMapper;
    private SectionMapper sectionMapper;

    @Autowired
    public BlockMapper(EntryMapper entryMapper, SectionMapper sectionMapper) {
        this.entryMapper = entryMapper;
        this.sectionMapper = sectionMapper;
    }

    public Block getBlock(BlockDto blockDto) {
        if (blockDto == null) return null;
        Block block = new Block();
        block.setId(blockDto.getId());
        block.setEntry(entryMapper.getEntryFrom(blockDto.getEntryDto()));
        block.setName(blockDto.getName());
        block.setStartDate(LocalDate.parse(blockDto.getStartDate()));
        block.setEndDate(LocalDate.parse(blockDto.getEndDate()));
        return block;
    }

    public BlockDto getBlockDto(Block block) {
        if (block == null) return null;
        BlockDto blockDto = new BlockDto();
        blockDto.setId(block.getId());
        blockDto.setEntryDto(entryMapper.getEntryDtoFrom(block.getEntry()));
        blockDto.setName(block.getName());
        blockDto.setStartDate(block.getStartDate().toString());
        blockDto.setEndDate(block.getEndDate().toString());
        return blockDto;
    }

    public BlockTrack getBlockTrack(BlockTrackDto dto) {
        if (dto == null)
            return null;

        BlockTrack bt = new BlockTrack(getBlock(dto.getBlockDto()), dto.getnStudent());
        List<Section> sections = dto.getSectionDtos().stream()
                .map(x -> sectionMapper.getSectionFrom(x))
                .collect(Collectors.toList());

        bt.setSections(sections);
        return bt;
    }

    public BlockTrackDto getBlockTrackDto(BlockTrack blockTrack){
        if (blockTrack == null) {
            return  null;
        }

        BlockTrackDto dto = new BlockTrackDto();
        dto.setBlockDto(getBlockDto(blockTrack.getBlock()));
        dto.setnStudent(blockTrack.getnStudent());
        dto.setSectionDtos(blockTrack.getSections()
                .stream()
                .map(x -> sectionMapper.getSectionDtoFrom(x))
                .collect(Collectors.toList()));

        return  dto;
    }
}
