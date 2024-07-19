package com.ems.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.EmployeeDto;
import com.ems.backend.model.dto.EmployeeMapper;
import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.repo.EmployeeRepo;
import com.ems.backend.service.EmployeeService;
import com.ems.backend.util.exception.ApiBusinessException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	public EmployeeRepo employeeRepo;

	@Override
	public EmployeeDto create(EmployeeDto employeeDto) {
		Employee employee = new Employee(
					employeeDto.getId(),
					employeeDto.getFirstName(),
					employeeDto.getLastName(),
					employeeDto.getEmail()
				);
		
		Employee result = employeeRepo.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(result);
	}

	@Override
	public EmployeeDto getEmployeeById(int id) {

		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Employee doesn't exit"));
		
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
			
		List<Employee> employeeList =  employeeRepo.findAll();
		
		List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
				
		for(Employee employee : employeeList) {
			employeeDtoList.add(EmployeeMapper.mapToEmployeeDto(employee));
		}
		
		return employeeDtoList;
	}

	@Override
	public EmployeeDto updateEmployee(int id, EmployeeDto employeeDto) {

		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Employee doesn't exit"));
		
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employee.getEmail());
		
		Employee updatedEmployee = employeeRepo.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}

	@Override
	public String deleteEmployee(int id) {
		
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Employee doesn't exit"));
		
		employeeRepo.delete(employee);
		
		return "Employee id: " + id + " have been deleted successfully";
	}

}
