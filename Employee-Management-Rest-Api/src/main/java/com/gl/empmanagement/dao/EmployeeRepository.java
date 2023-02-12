package com.gl.empmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.empmanagement.model.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

}
