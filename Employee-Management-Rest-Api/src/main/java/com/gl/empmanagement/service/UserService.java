package com.gl.empmanagement.service;

import com.gl.empmanagement.model.User;

public interface UserService {

	User saveAsUser(String username, String password);

	User saveAsAdmin(String username, String password);

}