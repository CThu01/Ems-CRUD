package com.ems.backend.model.dto;

import com.ems.backend.model.entity.MyUser;

public class UserMapper {

	public static MyUser mapToUser(UserDto userDto) {
		return new MyUser(
					userDto.getId(),
					userDto.getUsername(),
					userDto.getPasswrod(),
					userDto.getEmail(),
					userDto.getRole()
				);
	}
	
	public static UserDto mapToUserDto(MyUser user) {
		return new UserDto(
					user.getId(),
					user.getUsername(),
					user.getPassword(),
					user.getEmail(),
					user.getRole()
				);
	}
}
