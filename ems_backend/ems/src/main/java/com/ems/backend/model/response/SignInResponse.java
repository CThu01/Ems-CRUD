package com.ems.backend.model.response;

public record SignInResponse<T>(String username,T payload) {

	
}
