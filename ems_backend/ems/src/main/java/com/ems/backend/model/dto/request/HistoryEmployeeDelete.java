package com.ems.backend.model.dto.request;

import com.ems.backend.model.entity.Department;
import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.entity.Position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEmployeeDelete {

	Employee employee;
	String message;
}
