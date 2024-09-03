package com.ems.backend.util.exception;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationHandlerAspect {

	@Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController)")
	void apiMethod() {}
	
	@Before(value = "apiMethod() and args(..,result)", argNames = "result")
	public void handle(BindingResult result) {
		System.out.println("handling");
		if(result.hasErrors()) {
			
			System.out.println("There is validation error " + result.getAllErrors());
			throw new ApiValidationException(result);
		}
	}
}
