package edu.mum.se.poseidon.core.controllers.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.repositories.models.Block;

@Component
public class BlockMapper {
	
	private EntryMapper entryMapper;
	
	@Autowired
	public BlockMapper(EntryMapper entryMapper) {
		this.entryMapper = entryMapper;
	}
	
	public Block getBlock(BlockDto blockDto) {
		if(blockDto == null) return null;
		Block block = new Block();
		block.setId(blockDto.getId());
		block.setEntry(entryMapper.getEntryFrom(blockDto.getEntryDto()));
		block.setName(blockDto.getName());
		block.setStartDate(LocalDate.parse(blockDto.getStartDate()));
		block.setEndDate(LocalDate.parse(blockDto.getEndDate()));
		return block;
	}
	
	public BlockDto getBlockDto(Block block) {
		if(block == null) return null;
		BlockDto blockDto = new BlockDto();
		blockDto.setId(block.getId());
		blockDto.setEntryDto(entryMapper.getEntryDtoFrom(block.getEntry()));
		blockDto.setName(block.getName());
		blockDto.setStartDate(block.getStartDate().toString());
		blockDto.setEndDate(block.getEndDate().toString());
		return blockDto;
	}
}
