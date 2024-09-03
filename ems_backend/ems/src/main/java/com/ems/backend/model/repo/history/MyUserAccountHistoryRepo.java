package com.ems.backend.model.repo.history;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.backend.model.entity.history.MyUserAccountHistory;
import com.ems.backend.model.repo.BaseRepo;

@Repository
public interface MyUserAccountHistoryRepo extends BaseRepo<MyUserAccountHistory, Integer>{

	@Query("select max(uh.version) from MyUserAccountHistory uh where uh.myUserAccountId = :myUserAccountId")
	int findMaxVersionByMyUserAccountId(@Param("myUserAccountId") int myUserAccountId);
}
