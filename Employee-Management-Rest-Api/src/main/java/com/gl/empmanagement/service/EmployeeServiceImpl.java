package com.gl.empmanagement.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gl.empmanagement.dao.EmployeeRepository;
import com.gl.empmanagement.model.Employees;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public List<Employees> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employees findById(int id) {
		return this.employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("invalid student id"));
	}

	@Override
	public List<Employees> findByFirstName(String firstName) {
		Employees employee = new Employees();
		employee.setFirstName(firstName);
		ExampleMatcher eMatcher = ExampleMatcher.matching()
				.withMatcher(firstName, ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("id", "lastName", "email");
		Example<Employees> example = Example.of(employee, eMatcher);
		return this.employeeRepository.findAll(example);
	}

	@Override
	public List<Employees> sortedByFirstName(Direction dir) {
		return this.employeeRepository.findAll(Sort.by(dir, "firstName"));
	}

	@Override
	public Employees save(Employees employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public String deleteById(int id) {
		this.employeeRepository.deleteById(id);
		return "Employee deleted";
	}

}
