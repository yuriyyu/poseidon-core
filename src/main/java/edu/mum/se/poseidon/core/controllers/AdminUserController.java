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

import edu.mum.se.poseidon.core.controllers.dto.UserDto;
import edu.mum.se.poseidon.core.controllers.mapper.UserMapper;
import edu.mum.se.poseidon.core.repositories.models.users.User;
import edu.mum.se.poseidon.core.services.UserService;

@Controller
public class AdminUserController {
	private UserService userService;
	private UserMapper userMapper;
	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);
	
	@Autowired
	public AdminUserController(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}
	
	@RequestMapping(path="/users/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody UserDto userDto) {
		try {
			User user = this.userService.createUser(userDto);
			UserDto udo = userMapper.getUserDto(user);
			return new ResponseEntity<>(udo, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(path="/users/edit", method = RequestMethod.POST)
	public ResponseEntity<?> edit(@RequestBody UserDto userDto) {
		try {
			User user = this.userService.editUser(userDto);
			UserDto udo = userMapper.getUserDto(user);
			return new ResponseEntity<>(udo, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(path="/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable long id){
		try {
			User user = userService.getUser(id);
			UserDto udo = userMapper.getUserDto(user);
			return new ResponseEntity<>(udo, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/users", method = RequestMethod.GET)
	public ResponseEntity<?> getUserList(){
		try {
			List<User> users = userService.getUserList();
			List<UserDto> dtos = users.stream()
					.map(u -> userMapper.getUserDto(u))
					.collect(Collectors.toList());
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/users/{id}/delete", method = RequestMethod.GET)
	public ResponseEntity<?> deleteUser(@PathVariable long id){
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
