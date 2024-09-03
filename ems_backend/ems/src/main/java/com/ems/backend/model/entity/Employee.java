package com.ems.backend.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username",nullable = false)
	private String username;
	
	@Column(nullable = false)
	private Long salary;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String passport;
	
	private Long startDate;
	
	private Long dob;
	
	@Column(nullable = true)
	private String image;
	
	@ManyToOne
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position_id;
	
	
	
	
	@OneToOne(mappedBy = "employee",orphanRemoval = false)
	private MyUserAccount myUserAccount;
	
	

	public Employee( String username, Long salary, String address, String passport, Long startDate, Long dob,
			String image, Department department, Position position_id) {
		super();
		this.username = username;
		this.salary = salary;
		this.address = address;
		this.passport = passport;
		this.startDate = startDate;
		this.dob = dob;
		this.image = image;
		this.department = department;
		this.position_id = position_id;
	}
	
	
	
	
	
}	







