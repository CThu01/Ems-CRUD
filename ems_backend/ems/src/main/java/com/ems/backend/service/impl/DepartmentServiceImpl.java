package com.ems.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.mapper.DepartmentMapper;
import com.ems.backend.model.dto.request.DepartmentDto;
import com.ems.backend.model.entity.Department;
import com.ems.backend.model.repo.DepartmentRepo;
import com.ems.backend.service.DepartmentService;
import com.ems.backend.util.exception.ApiBusinessException;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	public String createDepartment(DepartmentDto departmentDto) {
		
		Department department = DepartmentMapper.maptoDepartment(departmentDto);
		Department createdDepartment =  departmentRepo.save(department);
		
		return createdDepartment.getName() + " is created successfully";
	}

	@Override
	public String updateDepartment(int id, DepartmentDto departmentDto) {
		
		Department department = departmentRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Department doesn't exit"));
		
		department.setName(departmentDto.getName());
		
		Department updatedDepartment = departmentRepo.save(department);
		
		return updatedDepartment.getName() + " is updated successfully";
	}

	@Override
	public String deleteDepartment(int id) {
		
		Department department = departmentRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Department doesn't exit"));

		departmentRepo.delete(department);
		
		return "Department id: " + id + " has been deleted successfully";
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {
		
		List<Department> departmentList = departmentRepo.findAll();
		List<DepartmentDto> departmentDtoList = new ArrayList<DepartmentDto>();
		
		for(Department d : departmentList) {
			departmentDtoList.add(DepartmentMapper.maptoDepartmentDto(d));
		}
		
		return departmentDtoList;
	}

}
