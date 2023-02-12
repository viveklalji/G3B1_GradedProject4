package com.gl.empmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;

import com.gl.empmanagement.model.Employees;

public interface EmployeeService {

	List<Employees> findAll();

	Employees findById(int id);

	List<Employees> findByFirstName(String firstName);

	List<Employees> sortedByFirstName(Direction dir);

	Employees save(Employees employee);

	String deleteById(int id);

}