package com.ems.backend.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ems.backend.model.entity.MyUser;
import com.ems.backend.model.repo.UserRepo;
import com.ems.backend.util.exception.ApiBusinessException;

public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<MyUser> userObj = userRepo.findByUserName(username);
		
		if(userObj.isPresent()) {
			MyUser userFromDataBase =  userObj.get();
			return User.builder()
					 		.username(userFromDataBase.getUsername())
					 		.password(userFromDataBase.getPassword())
					 		.roles(getRole(userFromDataBase))
					 		.build();
			
		} else {
			throw new ApiBusinessException("Account doesn't exit");
		}
		
	}

	private String[] getRole(MyUser userFromDataBase) {
		
		if(userFromDataBase.getRole() == null) {
			return new String[] {"MEMBER"};
		}
		return userFromDataBase.getRole().split(",");
	}

}
