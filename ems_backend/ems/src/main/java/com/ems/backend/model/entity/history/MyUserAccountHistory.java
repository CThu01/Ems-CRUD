package com.ems.backend.model.entity.history;

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
@AllArgsConstructor
@NoArgsConstructor
public class MyUserAccountHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String password;
	private String email;
	private String active;
	private String role;
	
	@Column(name = "my_user_id")
	private int myUserAccountId;
	private int version;
	
	@Column(name = "modify_date")
	private Long modifyDate;
	
	@Column(name = "modigy_by")
	private String modifyBy;

	public MyUserAccountHistory(String password, String email, String active, String role,int myUserAccountId, int version, Long modifyDate,
			String modifyBy) {
		super();
		this.password = password;
		this.email = email;
		this.active = active;
		this.role = role;
		this.myUserAccountId = myUserAccountId;
		this.version = version;
		this.modifyDate = modifyDate;
		this.modifyBy = modifyBy;
	}
	
	
	
}
