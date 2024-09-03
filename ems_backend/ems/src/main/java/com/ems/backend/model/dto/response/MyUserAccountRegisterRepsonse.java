package com.ems.backend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserAccountRegisterRepsonse {

	private String email;
	private String active;
	private String role;
}
