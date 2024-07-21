package com.ems.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ems.backend.model.dto.UserDto;
import com.ems.backend.model.dto.UserMapper;
import com.ems.backend.model.entity.MyUser;
import com.ems.backend.model.repo.UserRepo;
import com.ems.backend.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		MyUser user = new MyUser(
				 userDto.getId(),
				 userDto.getUsername(),
				 userDto.getPasswrod(),
				 userDto.getEmail(),
				 userDto.getRole()
				);
		
		MyUser userResult = userRepo.save(user);
		
		return UserMapper.mapToUserDto(userResult);
	}

}
