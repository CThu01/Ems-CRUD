package com.ems.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.request.DepartmentDto;

@Service
public interface DepartmentService {

	String createDepartment(DepartmentDto departmentDto);
	
	List<DepartmentDto> getAllDepartment();
	
	String updateDepartment(int id, DepartmentDto departmentDto);
	
	String deleteDepartment(int id);
	
}
