package com.ems.backend.service.listener;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.request.HistoryMyUserAccountEvent;
import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.entity.history.MyUserAccountHistory;
import com.ems.backend.model.repo.UserRepo;
import com.ems.backend.model.repo.history.MyUserAccountHistoryRepo;

import jakarta.transaction.Transactional;

@Service
public class MyUserAccountHistoryListener {

	@Autowired
	private MyUserAccountHistoryRepo myUserAccountHistoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@EventListener
	@Transactional
	public void handle(HistoryMyUserAccountEvent historyMyUserAccount) {
		
		MyUserAccountHistory myUserHistory = new MyUserAccountHistory(
					historyMyUserAccount.getMyUserAccount().getPassword(),
					historyMyUserAccount.getMyUserAccount().getEmail(),
					historyMyUserAccount.getMyUserAccount().getActive(),
					historyMyUserAccount.getMyUserAccount().getRole(),
					historyMyUserAccount.getMyUserAccount().getId(),
					1,
					new Date().getTime(),
					historyMyUserAccount.getEmail()
				);
		
		myUserAccountHistoryRepo.save(myUserHistory);
	}
	
	
	@EventListener
	@Transactional
	public void handle(MyUserAccount myUserAccount) {
		
		int version = myUserAccountHistoryRepo.findMaxVersionByMyUserAccountId(myUserAccount.getId());
		
		String email = userRepo.findByEmployeeId(myUserAccount.getEmployee().getId());
		
		MyUserAccountHistory myUserHistory = new MyUserAccountHistory(
												myUserAccount.getPassword(),
												myUserAccount.getEmail(),
												myUserAccount.getActive(),
												myUserAccount.getRole(),
												myUserAccount.getId(),
												version + 1,
												new Date().getTime(),
												email
											);
		
		myUserAccountHistoryRepo.save(myUserHistory);
		
	}
	
	
}










