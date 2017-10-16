package edu.mum.se.poseidon.core.controllers.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.core.controllers.dto.AuthenticationDto;
import edu.mum.se.poseidon.core.repositories.models.users.Admin;
import edu.mum.se.poseidon.core.repositories.models.users.Faculty;
import edu.mum.se.poseidon.core.repositories.models.users.Student;
import edu.mum.se.poseidon.core.repositories.models.users.User;

@Component
public class UserToDto {
	private final String rolePrefix = "ROLE_";
	public UserToDto() {
	}

	public AuthenticationDto getAuthenticationDto(User user) {
		
		if (user == null)
			return null;

		AuthenticationDto dto = new AuthenticationDto();
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setRoles(getRole(user.getClass()));
		
		return dto;
	}

	/*
	 * Gets roles by the instance of Class
	 * Because currently we dont have many roles.
	 */
	private List<String> getRole(Class<? extends User> user) {
		List<String> roles = new ArrayList<String>();

		if (user == Admin.class)
			roles.add(rolePrefix + "ADMIN");
		else if (user == Faculty.class)
			roles.add(rolePrefix +"FACULTY");
		else if(user == Student.class)
			roles.add(rolePrefix +"STUDENT");

		return roles;
	}
}
