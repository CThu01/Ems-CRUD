package com.ems.backend.util.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ems.backend.model.response.ErrorResponse;
import com.ems.backend.model.response.ErrorResponse.Type;


@RestControllerAdvice
public class ExceptionHandling {

	private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	ErrorResponse handle(ApiValidationException e) {
		
		logger.error("Api Validation Exception ...");
		for(String message : e.getErrors()) {
			logger.error(message);
		}
		return new ErrorResponse(Type.Validation, e.getErrors());
	}
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	ErrorResponse handle(ApiBusinessException e) {
		logger.error(e.getMessage());
		return new ErrorResponse(Type.Business, List.of(e.getMessage()));
	}
	
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorResponse handle(Exception e) {
		logger.error(e.getMessage());
		e.printStackTrace();
		return new ErrorResponse(Type.Platform, List.of(e.getMessage()));
	}
	
	
}










