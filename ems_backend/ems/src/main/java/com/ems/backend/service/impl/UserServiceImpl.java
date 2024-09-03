package com.ems.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.mapper.MyUserAccountMapper;
import com.ems.backend.model.dto.request.HistoryMyUserAccountEvent;
import com.ems.backend.model.dto.request.MyUserAccountRegister;
import com.ems.backend.model.dto.response.MyUserAccountRegisterRepsonse;
import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.repo.EmployeeRepo;
import com.ems.backend.model.repo.UserRepo;
import com.ems.backend.service.UserService;
import com.ems.backend.util.exception.ApiBusinessException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MyUserAccountMapper myUserAccountMapper;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Override
	public MyUserAccountRegisterRepsonse createUser(MyUserAccountRegister myUserAccountDto) {
		
		MyUserAccount user = myUserAccountMapper.mapToMyUserAccount(myUserAccountDto);
		
		MyUserAccount userResult = userRepo.save(user);
		
		// record history
		String email = userRepo.findByEmployeeId(myUserAccountDto.getEmplyeeId());
		eventPublisher.publishEvent(new HistoryMyUserAccountEvent(
												userResult,email
											));
		
		return myUserAccountMapper.mapToMyUserAccountDtoResponse(userResult);
	}

	@Override
	public List<MyUserAccountRegisterRepsonse> getAllUserAccount() {
		
		List<MyUserAccount> myUserAccountList = userRepo.findAll();
		
		List<MyUserAccountRegisterRepsonse> myUserAccountDtoList = new ArrayList<MyUserAccountRegisterRepsonse>();
		
		for(MyUserAccount account : myUserAccountList) {
			myUserAccountDtoList.add(myUserAccountMapper.mapToMyUserAccountDtoResponse(account));
		}
		
		return myUserAccountDtoList;
	}

	@Override
	public MyUserAccountRegisterRepsonse updateUserAccount(int id, MyUserAccountRegister myUserAccountDto) {
		
		MyUserAccount myUserAccount = userRepo.findById(id).orElseThrow(() -> new ApiBusinessException("User Account not found"));

		myUserAccount.setPassword(myUserAccountDto.getPassword());
		myUserAccount.setEmail(myUserAccountDto.getEmail());
		myUserAccount.setActive(myUserAccountDto.getActive());
		myUserAccount.setRole(myUserAccountDto.getRole());
		
		myUserAccount.setEmployee(employeeRepo.findById(myUserAccountDto.getEmplyeeId())
												.orElseThrow(() -> new ApiBusinessException("Employee not found")));
		
		MyUserAccount updatedUserAccount = userRepo.save(myUserAccount);
		
		// record history
		eventPublisher.publishEvent(updatedUserAccount);
		
		return myUserAccountMapper.mapToMyUserAccountDtoResponse(updatedUserAccount);
	}

	@Override
	public String deleteUserAccount(int id) {
		
		MyUserAccount myUserAccount = userRepo.findById(id).orElseThrow(() -> new ApiBusinessException("User Account doesn't exit"));
		
		userRepo.delete(myUserAccount);
		
		return "User id : " + id + "have been deleted successfully";
	}

}






