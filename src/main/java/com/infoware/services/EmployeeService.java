package com.infoware.services;

import org.springframework.data.domain.Page;

import com.infoware.entities.Employee;

public interface EmployeeService {
	// to get employee by id
	Employee getEmployee(int employeeId);
	
	//get all employee using pagination
	
	
	// create employee
	Employee addEmployee(Employee employee);
	
	//update employee entity
	Employee updateEmployee(Employee employee);
	
	//delete employee
	void deleteEmployee(int employeeId);
	
	//get all employee
	Page<Employee> getAllEmployee(int pageNumber,int pageSize);
}
