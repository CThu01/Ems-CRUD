package com.ems.backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ems.backend.model.dto.request.EmployeeDto;
import com.ems.backend.model.dto.response.DynamicSearchDto;
import com.ems.backend.model.dto.response.EmployeeDtoResponse;

@Service
public interface EmployeeService {

	EmployeeDtoResponse create (EmployeeDto employeeDto,MultipartFile photo) throws IOException;
	
	EmployeeDtoResponse getEmployeeById(int id);
	
	List<EmployeeDtoResponse> getAllEmployee();
	
	EmployeeDtoResponse updateEmployee(int id,EmployeeDto employeeDto,MultipartFile photo) throws IOException;
	
	String deleteEmployee(int id);
	
	List<DynamicSearchDto> search(String keyword);
	
}




