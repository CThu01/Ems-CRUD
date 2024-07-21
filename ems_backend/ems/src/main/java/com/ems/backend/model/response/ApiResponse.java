package com.ems.backend.model.response;

import java.util.Date;

public record ApiResponse<T>(boolean status,T payload,Long currentTime) {

	public static<T> ApiResponse<T> success(T payload){
		return new ApiResponse<T>(true, payload,new Date().getTime());
	}
	
	public static<T> ApiResponse<T> fail(T payload){
		return new ApiResponse<T>(false, payload,new Date().getTime());
	}

}
