package com.ems.backend.model.dto.request;

import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.entity.Position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ems.backend.model.entity.Department;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEmployeeCreate{
		Employee employee;
		Position position;
		Department department;
		String email;		 
}

