package com.ems.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.backend.model.dto.response.EmployeeDtoResponse;
import com.ems.backend.model.response.ApiResponse;
import com.ems.backend.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("employee")
public class MemberApi {

	@Autowired
	private EmployeeService employeeService;
	
	public ApiResponse<EmployeeDtoResponse> getEmployee(int id){
		return ApiResponse.success(employeeService.getEmployeeById(id));
	}
	
}
