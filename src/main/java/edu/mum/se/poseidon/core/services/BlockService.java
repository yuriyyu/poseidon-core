package edu.mum.se.poseidon.core.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.controllers.mapper.EntryMapper;
import edu.mum.se.poseidon.core.repositories.BlockRepository;
import edu.mum.se.poseidon.core.repositories.models.Block;

@Service
public class BlockService {

	private BlockRepository blockRepository;
	private EntryMapper entryMapper;
	
	@Autowired
	public BlockService(BlockRepository blockRepository, EntryMapper entryMapper) {
		this.blockRepository = blockRepository;
		this.entryMapper = entryMapper;
	}
	
	public Block createBlock(BlockDto blockDto) {
		Block block = new Block();
		block.setEntry(entryMapper.getEntryFrom(blockDto.getEntryDto()));
		block.setName(blockDto.getName());
		block.setStartDate(LocalDate.parse(blockDto.getStartDate()));
		block.setEndDate(LocalDate.parse(blockDto.getEndDate()));
		block = blockRepository.save(block);
		return block;
	}
	
	public Block editBlock(BlockDto blockDto) {
		Block block = blockRepository.findOne(blockDto.getId());
		block.setEntry(entryMapper.getEntryFrom(blockDto.getEntryDto()));
		block.setName(blockDto.getName());
		block.setStartDate(LocalDate.parse(blockDto.getStartDate()));
		block.setEndDate(LocalDate.parse(blockDto.getEndDate()));
		block = blockRepository.save(block);
		return block;
	}
	
	public List<Block> getBlockList(){
    	return blockRepository.findAllByDeleted(false);
    }
    
    public Block getBlock(long blockId) {
    	return blockRepository.findOne(blockId);
    }
    
    public void deleteBlock(long blockId) {
    	Block block = blockRepository.findOne(blockId);
    	block.setDeleted(true);
    	blockRepository.save(block);
    }
}
