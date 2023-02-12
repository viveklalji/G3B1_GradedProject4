package com.gl.empmanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.empmanagement.model.User;
import com.gl.empmanagement.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "Save new user role", notes = "This API allows you to save new user as user role.")
	@PostMapping("/userRegister")
	public User saveAsUser(String username, String password) {
		return this.userService.saveAsUser(username, password);
	}

	@ApiOperation(value = "Save new admin role", notes = "This API allows you to save new user as admin role.")
	@PostMapping("/adminRegister")
	public User saveAsAdmin(String username, String password) {
		return this.userService.saveAsAdmin(username, password);
	}
}
