package com.ems.backend.service;

import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.UserDto;

@Service
public interface UserService {

	UserDto createUser(UserDto userdto);
	
}
