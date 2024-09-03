package com.ems.backend.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.repo.UserRepo;
import com.ems.backend.util.exception.ApiBusinessException;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<MyUserAccount> userObj = userRepo.findByEmail(username);
		
		if(userObj.isPresent()) {
			MyUserAccount userFromDataBase =  userObj.get();
			
			return User.builder()
					 		.username(userFromDataBase.getEmail())
					 		.password(userFromDataBase.getPassword())
					 		.roles(getRole(userFromDataBase))
					 		.build();
			
		} else {
			throw new ApiBusinessException("Account doesn't exit");
		}
		
	}

	private String[] getRole(MyUserAccount userFromDataBase) {
		
		if(userFromDataBase.getRole() == null) {
			return new String[] {"MEMBER"};
		}
		
		return userFromDataBase.getRole().split(",");
	}

}
