package com.ems.backend.model.dto.response;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDtoResponse {

	private String username;
	private String salary;
	private String address;
	private String passport;
	private String dob;
	private Map<String, String> image;
	private String department;
	private String position;
}
