package com.ems.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.EmployeeDto;

@Service
public interface EmployeeService {

	EmployeeDto create(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeById(int id);
	
	List<EmployeeDto> getAllEmployee();
	
	EmployeeDto updateEmployee(int id,EmployeeDto employeeDto);
	
	String deleteEmployee(int id);
}
