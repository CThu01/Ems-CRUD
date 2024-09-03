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

import com.ems.backend.model.dto.request.DepartmentDto;
import com.ems.backend.model.response.ApiResponse;
import com.ems.backend.service.DepartmentService;

@CrossOrigin("*")
@RestController
@RequestMapping("department")
public class DepartmentApi {

	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public ApiResponse<String> createDepartment(@Validated @RequestBody DepartmentDto departmentDto,BindingResult result){
		return ApiResponse.success(departmentService.createDepartment(departmentDto));
	}
	
	@GetMapping
	public ApiResponse<List<DepartmentDto>> getAllDepartment(){
		return ApiResponse.success(departmentService.getAllDepartment());
	}
	
	@PutMapping("{id}")
	public ApiResponse<String> updateDepartment(@PathVariable int id,@Validated @RequestBody DepartmentDto departmentDto,BindingResult result){
		return ApiResponse.success(departmentService.updateDepartment(id, departmentDto));
	}
	
	@DeleteMapping("{id}")
	public ApiResponse<String> deleteDepartment(@PathVariable int id){
		return ApiResponse.success(departmentService.deleteDepartment(id));
	}
	
}







