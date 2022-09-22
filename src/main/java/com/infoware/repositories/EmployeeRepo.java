package com.infoware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoware.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
