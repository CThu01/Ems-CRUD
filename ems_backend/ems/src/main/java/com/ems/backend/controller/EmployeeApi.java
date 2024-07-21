package com.ems.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.backend.model.dto.EmployeeDto;
import com.ems.backend.model.response.ApiResponse;
import com.ems.backend.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("employee")
public class EmployeeApi {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ApiResponse<EmployeeDto> createEmployee(@Validated @RequestBody EmployeeDto employeeDto,BindingResult result){
		return ApiResponse.success(employeeService.create(employeeDto));
	}
	
	@GetMapping("{id}")
	public ApiResponse<EmployeeDto> getEmployeeById(@PathVariable("id") int id){
		return ApiResponse.success(employeeService.getEmployeeById(id));
	}
	
	@GetMapping
	public ApiResponse<List<EmployeeDto>> getAllEmployee(){
		return ApiResponse.success(employeeService.getAllEmployee());
	}
	
	@PutMapping("{id}")
	public ApiResponse<EmployeeDto> updateEmployee(@PathVariable("id") int id,@Validated @RequestBody EmployeeDto employeeDto,BindingResult result){
		return ApiResponse.success(employeeService.updateEmployee(id, employeeDto));
	}
	
	@DeleteMapping("{id}")
	public ApiResponse<String> deleteEmployee(@PathVariable("id") int id){
		return ApiResponse.success(employeeService.deleteEmployee(id));
	}
	
	
	
}










