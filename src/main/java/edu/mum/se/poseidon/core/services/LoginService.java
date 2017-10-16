package edu.mum.se.poseidon.core.services;

import edu.mum.se.poseidon.core.repositories.UserRepository;
import edu.mum.se.poseidon.core.repositories.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginService {

	private UserRepository userRepository;

	@Autowired
	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*
	 * WILL BE REMOVED
	 */
	public void login(String userName, String password) {
		User user = userRepository.findUserByUsername(userName);
	}

	public User getUser(String username) {
		return userRepository.findUserByUsername(username);
	}
}
