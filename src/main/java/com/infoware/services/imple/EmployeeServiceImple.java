package com.infoware.services.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.infoware.entities.Contact;
import com.infoware.entities.Employee;
import com.infoware.repositories.ContactRepo;
import com.infoware.repositories.EmployeeRepo;
import com.infoware.services.EmployeeService;

@Service
public class EmployeeServiceImple implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	@Override
	public Employee getEmployee(int employeeId) {
		return this.employeeRepo.findById(employeeId).orElseThrow();
	
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return this.employeeRepo.save(employee);
		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		//deleting the tuples saved in contact table of the particular employee
		 Employee employee2 = this.employeeRepo.findById(employee.getId()).orElseThrow();
		 List<Contact> contacts = employee2.getContacts();
		 employee2.setContacts(null);
		 this.employeeRepo.save(employee2);
		 for(Contact c: contacts) {
			Contact contact= this.contactRepo.findById(c.getId()).orElseThrow();
			 this.contactRepo.delete(contact);
		 }
		 //saving new contact for the employee
		return this.employeeRepo.save(employee);
		 
	}

	@Override
	public void deleteEmployee(int employeeId) {
		this.employeeRepo.deleteById(employeeId);

	}
	
	public Page getAllEmployee(int pageNumber,int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Employee> page = this.employeeRepo.findAll(pageable);
		return page;
	}

}
