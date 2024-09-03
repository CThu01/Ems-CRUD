   package com.ems.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.backend.model.dto.request.MyUserAccountLogin;
import com.ems.backend.model.dto.request.MyUserAccountRegister;
import com.ems.backend.model.dto.response.MyUserAccountRegisterRepsonse;
import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.response.ApiResponse;
import com.ems.backend.model.response.SignInResponse;
import com.ems.backend.security.config.MyUserDetailService;
import com.ems.backend.security.jwt.JwtService;
import com.ems.backend.service.UserService;
import com.ems.backend.util.exception.ApiBusinessException;

@CrossOrigin("*")
@RestController
@RequestMapping("account")
public class UserAccApi {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MyUserDetailService myUserDetailService;

	@PostMapping("/login")
	public ApiResponse<SignInResponse<String>> signIn(@RequestBody @Validated MyUserAccountLogin myUserAccountLogin,BindingResult result){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(myUserAccountLogin.getEmail(), myUserAccountLogin.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return ApiResponse.success(
					new SignInResponse<String>(
							myUserAccountLogin.getEmail(), 
							jwtService.generateToken(myUserDetailService.loadUserByUsername(myUserAccountLogin.getEmail())) 
					));
		} else {
			throw new ApiBusinessException("Wrong username and password");
		}
	}
	

	
	
}








