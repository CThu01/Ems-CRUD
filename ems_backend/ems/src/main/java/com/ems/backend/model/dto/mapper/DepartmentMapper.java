package com.ems.backend.model.dto.mapper;

import com.ems.backend.model.dto.request.DepartmentDto;
import com.ems.backend.model.entity.Department;

public class DepartmentMapper {
	
	public static DepartmentDto maptoDepartmentDto(Department department) {
		return new DepartmentDto(department.getName());
	}
	
	public static Department maptoDepartment(DepartmentDto departmentDto) {
		return new Department(departmentDto.getName());
	}

}
