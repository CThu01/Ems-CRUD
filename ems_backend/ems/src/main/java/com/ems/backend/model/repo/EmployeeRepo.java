package com.ems.backend.model.repo;

import org.springframework.stereotype.Repository;

import com.ems.backend.model.entity.Employee;

@Repository
public interface EmployeeRepo extends BaseRepo<Employee, Integer>{

	
}
