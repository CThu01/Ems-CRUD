package com.ems.backend.model.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.backend.model.entity.MyUserAccount;

@Repository
public interface UserRepo extends BaseRepo<MyUserAccount, Integer>{

	Optional<MyUserAccount> findByEmail(String email);
	
	@Query("select u.email from MyUserAccount u where u.employee.id = :employeeId")
	String findByEmployeeId(@Param("employeeId") int employeeId);

}
