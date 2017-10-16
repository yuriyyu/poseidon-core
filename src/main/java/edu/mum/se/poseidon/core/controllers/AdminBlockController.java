package edu.mum.se.poseidon.core.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.controllers.dto.BlockDto;
import edu.mum.se.poseidon.core.controllers.mapper.BlockMapper;
import edu.mum.se.poseidon.core.repositories.models.Block;
import edu.mum.se.poseidon.core.services.BlockService;

@Controller
public class AdminBlockController {

	private BlockService blockService;
	private BlockMapper blockMapper;
	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);
	
	@Autowired
	public AdminBlockController(BlockService blockService, BlockMapper blockMapper) {
		this.blockService = blockService;
		this.blockMapper = blockMapper;
	}
	
	@RequestMapping(path="/blocks/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BlockDto blockDto) {
		Block block = this.blockService.createBlock(blockDto);
		BlockDto bdo = blockMapper.getBlockDto(block);
		return new ResponseEntity<>(bdo, HttpStatus.OK);
	}
	
	@RequestMapping(path="/blocks/edit", method = RequestMethod.POST)
	public ResponseEntity<?> edit(@RequestBody BlockDto userDto) {
		Block block = this.blockService.editBlock(userDto);
		BlockDto bdo = blockMapper.getBlockDto(block);
		return new ResponseEntity<>(bdo, HttpStatus.OK);
	}
	
	@RequestMapping(path="/blocks/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlock(@PathVariable long id){
		Block block = blockService.getBlock(id);
		BlockDto bdo = blockMapper.getBlockDto(block);
		return new ResponseEntity<>(bdo, HttpStatus.OK);
	}
	
	@RequestMapping(path="/blocks", method = RequestMethod.GET)
	public ResponseEntity<?> getBlockList(){
		List<Block> blocks = blockService.getBlockList();
		List<BlockDto> dtos = blocks.stream()
				.map(b -> blockMapper.getBlockDto(b))
				.collect(Collectors.toList());
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(path="/blocks/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> deleteUser(@PathVariable long id){
		blockService.deleteBlock(id);
		return new ResponseEntity<>(new BlockDto(), HttpStatus.OK);
	}
}
