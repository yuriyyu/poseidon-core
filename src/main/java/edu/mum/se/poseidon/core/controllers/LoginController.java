package edu.mum.se.poseidon.core.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.core.controllers.dto.AuthenticationDto;
import edu.mum.se.poseidon.core.controllers.mapper.UserToDto;
import edu.mum.se.poseidon.core.repositories.models.users.User;
import edu.mum.se.poseidon.core.services.LoginService;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	private LoginService loginService;
	private UserToDto userToAuthenticationDto;

	@Autowired
	public LoginController(LoginService loginService, UserToDto dto) {
		this.loginService = loginService;
		this.userToAuthenticationDto = dto;
	}

	@RequestMapping(path = "/logins/{username}", method = RequestMethod.GET)
	public ResponseEntity<AuthenticationDto> getUserByUsername(
			@PathVariable(name = "username", required = true) String username) {

		User user = loginService.getUser(username);
		AuthenticationDto dto = userToAuthenticationDto.getAuthenticationDto(user);

		return new ResponseEntity<AuthenticationDto>(dto, HttpStatus.OK);
	}
}
