package com.ems.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
public class PasswordEncryptor {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void encryptPassword() {
		String password = "123456789";
		
		System.out.println(passwordEncoder.encode(password));
	}
	
	
}
