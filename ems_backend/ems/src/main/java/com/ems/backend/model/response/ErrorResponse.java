package com.ems.backend.model.response;

import java.util.Collections;
import java.util.List;

public record ErrorResponse(Type type, List<String> messages){

	public ErrorResponse(Type type,String message) {
		this(type,Collections.singletonList(message));
	}
	
	public enum Type{
		Validation, Business, Platform
	}
	
	public enum ErrorCode{
		URL_NOT_FOUND
	}
}
