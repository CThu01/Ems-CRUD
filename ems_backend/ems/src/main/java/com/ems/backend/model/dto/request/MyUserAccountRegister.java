package com.ems.backend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserAccountRegister {

	private String password;
	private String email;
	private String active;
	private String role;
	
	private int emplyeeId;
	
}
