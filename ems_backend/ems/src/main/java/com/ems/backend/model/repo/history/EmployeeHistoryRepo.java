package com.ems.backend.model.repo.history;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.backend.model.entity.history.EmployeeHistory;
import com.ems.backend.model.repo.BaseRepo;

@Repository
public interface EmployeeHistoryRepo extends BaseRepo<EmployeeHistory, Integer>{

	@Query("select max(eh.version) from EmployeeHistory eh where eh.employeeId= :employeeId")
	int findMaxVersionByEmployeeId(@Param("employeeId") int employeeId);
}
