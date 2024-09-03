package com.ems.backend.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	@Size(message = "Required username",min = 2)
	private String username;
	
	@NotNull(message = "Required salary")
	private Long salary;
	
	@Size(message = "Required address",min = 5)
	private String address;
	
	@NotBlank(message = "Required passport")
	private String passport;
	private String dob;
	private String image;
	
	@NotNull(message = "Required department")
	private Integer department;
	
	@NotNull(message = "Required position")
	private Integer position;
	
}
