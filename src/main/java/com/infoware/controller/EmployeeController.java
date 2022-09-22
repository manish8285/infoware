package com.infoware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infoware.entities.Employee;
import com.infoware.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	// add new employee
	@PostMapping("/")
	ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		Employee employee2 = this.employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(employee2,HttpStatus.CREATED);
	}
	
	
	// get employee by id
	@GetMapping("/{empId}")
	ResponseEntity<Employee> getEmployee(@PathVariable("empId") Integer empId){
		return ResponseEntity.ok(this.employeeService.getEmployee(empId));
	}
	
	// update employee entity
	
	@PutMapping("/")
	ResponseEntity<Employee> updateeEmployee(@RequestBody Employee employee){
		return ResponseEntity.ok(this.employeeService.updateEmployee(employee));
	}
	
	//delete existing employee
	@DeleteMapping("/{empId}")
	ResponseEntity<String> deleteEmployee(@PathVariable Integer empId){
		this.employeeService.deleteEmployee(empId);
		return ResponseEntity.ok("Employee has been deleted successfully");
	}
	
	//get all employee
	@GetMapping("/employees")
	public ResponseEntity<Page<Employee>>getALlEmployee(@RequestParam(value="pageNumber",required = false,defaultValue = "0") Integer pageNumber,
			@RequestParam(value="pageSize",required = false,defaultValue = "5") Integer pageSize){
		return ResponseEntity.ok(this.employeeService.getAllEmployee(pageNumber, pageSize));
	}
	
	
}
