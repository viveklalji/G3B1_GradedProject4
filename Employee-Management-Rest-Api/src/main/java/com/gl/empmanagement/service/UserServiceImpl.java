package com.gl.empmanagement.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.empmanagement.dao.UserRepository;
import com.gl.empmanagement.model.Role;
import com.gl.empmanagement.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	Role user = new Role("ROLE_USER");
	Role admin = new Role("ROLE_ADMIN");

	@Override
	public User saveAsUser(String username, String password) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.addRole(user);
		return this.userRepository.save(newUser);
	}

	@Override
	public User saveAsAdmin(String username, String password) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.addRole(admin);
		// newUser.addRole(user);
		return this.userRepository.save(newUser);
	}

}
