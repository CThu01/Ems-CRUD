package com.ems.backend.model.entity.history;

import java.util.Date;

import org.hibernate.annotations.Collate;

import com.ems.backend.model.dto.request.DepartmentDto;
import com.ems.backend.model.dto.request.EmployeeDto;
import com.ems.backend.model.dto.request.PositionDto;
import com.ems.backend.model.entity.Department;
import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.entity.Position;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private Long salary;
	private String address;
	private String passport;
	private Long startDate;
	private Long dob;
	private String image;
	private String department;
	private String position;
//	private String action;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	private int version;
	
	@Column(name = "modify_date")
	private Long modifyDate;
	
	@Column(name = "modify_by")
	private String modifyBy;

	public EmployeeHistory(Employee employee, Position position, Department department,String email, int version) {
		
		this.username = employee.getUsername();
		this.salary = employee.getSalary();
		this.address = employee.getAddress();
		this.passport = employee.getPassport();
		this.startDate = employee.getStartDate();
		this.dob = employee.getDob();
		this.image = employee.getImage();
		this.department = department.getName();
		this.position = position.getName();
		
		this.employeeId= employee.getId();
		this.version = version;
		this.modifyDate = new Date().getTime();
		this.modifyBy = email;
	}
	
	
}




