package com.gl.empmanagement.controller;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.empmanagement.model.Employees;
import com.gl.empmanagement.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	@ApiOperation(value = "Get all employee details ", notes = "This API gets you all the employee's details.")
	@GetMapping("/list")
	public List<Employees> ListAllEmployees() {
		return this.employeeService.findAll();
	}

	@ApiOperation(value = "Get Employee details by ID", notes = "This API gets you the employee details by their ID.")
	@GetMapping("/list/{id}")
	public Employees findById(@PathVariable("id") int id) {
		return this.employeeService.findById(id);
	}

	@ApiOperation(value = "Get employee details with their first name.", notes = "This API gets you the employee details with their first name")
	@GetMapping("/search/{firstName}")
	public List<Employees> findByFirstName(@PathVariable("firstName") String firstName) {
		return this.employeeService.findByFirstName(firstName);
	}

	@ApiOperation(value = "Get employee details with their first name's sorted", notes = "This API gets you the employee details with their first name's sorted.")
	@GetMapping("/list/sort/{order}")
	public List<Employees> sortedByFirstName(@PathVariable("order") Direction dir) {
		return this.employeeService.sortedByFirstName(dir);
	}

	@ApiOperation(value = "Save Employee", notes = "This API allows you to save employee to Employee table.")
	@PostMapping("/new")
	public Employees saveEmployee(Employees employee) {
		return employeeService.save(employee);
	}

	@ApiOperation(value = "Delete Employee", notes = "This API allows you to delete an employee from Employee table.")
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable int id) {
		return employeeService.deleteById(id);
	}

	@ApiOperation(value = "Update Employee", notes = "This API allows you to update an existing employee's details.")
	@PostMapping("/update/{id}")
	public Employees updateemployee(@PathVariable int id, Employees employee) {

		// get employee from database by id
		Employees existingEmployee = employeeService.findById(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		// save updated employee object
		return employeeService.save(existingEmployee);
	}
}
