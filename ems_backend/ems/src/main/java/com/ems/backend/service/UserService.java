package com.ems.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.request.MyUserAccountRegister;
import com.ems.backend.model.dto.response.MyUserAccountRegisterRepsonse;

@Service
public interface UserService {

	MyUserAccountRegisterRepsonse createUser(MyUserAccountRegister userdto);
	
	List<MyUserAccountRegisterRepsonse> getAllUserAccount();
	
	MyUserAccountRegisterRepsonse updateUserAccount(int id, MyUserAccountRegister myUserAccountDto);
	
	String deleteUserAccount(int id);
	
}
