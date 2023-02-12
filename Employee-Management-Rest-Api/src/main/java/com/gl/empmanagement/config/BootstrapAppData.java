package com.gl.empmanagement.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.gl.empmanagement.dao.EmployeeRepository;
import com.gl.empmanagement.dao.UserRepository;
import com.gl.empmanagement.model.Employees;
import com.gl.empmanagement.model.Role;
import com.gl.empmanagement.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {

	private final UserRepository userRepository;
	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeData(ApplicationReadyEvent readyEvent) {

		Employees ravi = new Employees("Ravi", "Kumar", "ravi@gmail.com");
		Employees harish = new Employees("Harish", "Prasad", "harish@gmail.com");
		Employees ramesh = new Employees("Ramesh", "Patil", "ramesh@gmail.com");
		Employees krishna = new Employees("Krishna", "Patel", "krishna@gmail.com");

		this.employeeRepository.save(ravi);
		this.employeeRepository.save(harish);
		this.employeeRepository.save(ramesh);
		this.employeeRepository.save(krishna);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void initializeUsersData(ApplicationReadyEvent readyEvent) {

		User kiran = new User("kiran", passwordEncoder.encode("welcome"));
		User vinay = new User("vinay", passwordEncoder.encode("welcome"));

		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");

		kiran.addRole(userRole);

		vinay.addRole(userRole);
		vinay.addRole(adminRole);

		this.userRepository.save(kiran);
		this.userRepository.save(vinay);

	}

}
