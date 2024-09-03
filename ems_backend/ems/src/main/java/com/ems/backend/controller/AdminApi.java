package com.ems.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ems.backend.model.dto.request.EmployeeDto;
import com.ems.backend.model.dto.request.MyUserAccountRegister;
import com.ems.backend.model.dto.response.DynamicSearchDto;
import com.ems.backend.model.dto.response.EmployeeDtoResponse;
import com.ems.backend.model.dto.response.MyUserAccountRegisterRepsonse;
import com.ems.backend.model.response.ApiResponse;
import com.ems.backend.service.EmployeeService;
import com.ems.backend.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("admin")
public class AdminApi {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ApiResponse<EmployeeDtoResponse> createEmployee(
			@RequestParam("photo") MultipartFile photo,
			@Validated @ModelAttribute EmployeeDto employeeDto,BindingResult result) throws IOException{
		return ApiResponse.success(employeeService.create(employeeDto,photo));
	}
	
	@GetMapping("{id}")
	public ApiResponse<EmployeeDtoResponse> getEmployeeById(@PathVariable("id") int id){
		return ApiResponse.success(employeeService.getEmployeeById(id));
	}
	
	@GetMapping
	public ApiResponse<List<EmployeeDtoResponse>> getAllEmployee(){
		return ApiResponse.success(employeeService.getAllEmployee());
	}
	
	@PutMapping("{id}")
	public ApiResponse<EmployeeDtoResponse> updateEmployee(
			@PathVariable("id") int id,
			@RequestParam("photo") MultipartFile photo,
			@Validated @ModelAttribute EmployeeDto employeeDto,BindingResult result) throws IOException{
		return ApiResponse.success(employeeService.updateEmployee(id, employeeDto,photo));
	}
	
	@DeleteMapping("{id}")
	public ApiResponse<String> deleteEmployee(@PathVariable("id") int id){
		return ApiResponse.success(employeeService.deleteEmployee(id));
	}
	
	@GetMapping("search/{keyword}")
	public ApiResponse<List<DynamicSearchDto>> searchWithKeyword(@PathVariable("keyword") String keyword){
		return ApiResponse.success(employeeService.search(keyword));
	}
	
	@PostMapping("/register")
	public ApiResponse<MyUserAccountRegisterRepsonse> register(@RequestBody @Validated MyUserAccountRegister register,BindingResult result){
		return ApiResponse.success(userService.createUser(register));
	}

	
}










