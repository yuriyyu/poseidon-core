package edu.mum.se.poseidon.core.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.controllers.mapper.EntryMapper;
import edu.mum.se.poseidon.core.repositories.BlockRepository;
import edu.mum.se.poseidon.core.repositories.EntryRepository;
import edu.mum.se.poseidon.core.repositories.models.Block;
import edu.mum.se.poseidon.core.repositories.models.Entry;

@Service
public class BlockService {

	private BlockRepository blockRepository;
	private EntryMapper entryMapper;
	private EntryRepository entryRepository;
	
	@Autowired
	public BlockService(BlockRepository blockRepository, EntryMapper entryMapper, EntryRepository entryRepository) {
		this.blockRepository = blockRepository;
		this.entryMapper = entryMapper;
		this.entryRepository = entryRepository;
	}
	
	public Block createBlock(BlockDto blockDto) {
		
		Entry entry = entryRepository.findOne(blockDto.getEntryDto().getId());
		if(entry == null) {
			throw new RuntimeException("The entry doesn't exists!");
		}
		
		Block block = new Block();
		block.setEntry(entryMapper.getEntryFrom(blockDto.getEntryDto()));
		block.setName(blockDto.getName());
		block.setStartDate(LocalDate.parse(blockDto.getStartDate()));
		block.setEndDate(LocalDate.parse(blockDto.getEndDate()));
		block = blockRepository.save(block);
		return block;
	}
	
	public Block editBlock(BlockDto blockDto) {
		
		Entry entry = entryRepository.findOne(blockDto.getEntryDto().getId());
		if(entry == null) {
			throw new RuntimeException("THe entry doesn't exists!");
		}
		
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
    
    public List<Block> autoGenerate(Entry entry, int numberOfBlocks){
    	
    	if(entry == null) {
    		throw new RuntimeException("The entry doesn't exists!");
    	}
    	
    	List<Block> blocks = new ArrayList<>();
    	LocalDate tmpStartDate = entry.getStartDate();
    	for(int i = 0; i < numberOfBlocks; i++) {
    		Block block = new Block();
    		block.setEntry(entry);
    		block.setName(entry.getName() + "-B" + (i + 1));
    		LocalDate startDate = tmpStartDate
					.plusDays(8 - tmpStartDate.getDayOfWeek().getValue());
    		block.setStartDate(startDate);
    		block.setEndDate(startDate.plusDays(25));
    		tmpStartDate = block.getEndDate();
    		block = blockRepository.save(block);
    		blocks.add(block);
    	}
    	return blocks;
    }
}
