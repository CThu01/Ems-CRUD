package com.ems.backend.service.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.request.HistoryEmployeeDelete;
import com.ems.backend.model.dto.request.HistoryEmployeeCreate;
import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.entity.history.EmployeeHistory;
import com.ems.backend.model.repo.UserRepo;
import com.ems.backend.model.repo.history.EmployeeHistoryRepo;
import com.ems.backend.security.jwt.JwtService;
import com.ems.backend.util.exception.ApiBusinessException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class EmployeeHistoryListener {

	@Autowired
	private EmployeeHistoryRepo employeeHistoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;

	
	@EventListener
	@Transactional
	public void handle(HistoryEmployeeCreate saveEmployeeEvent) {
		
		EmployeeHistory employeeHistory = new EmployeeHistory(
				saveEmployeeEvent.getEmployee(),
				saveEmployeeEvent.getPosition(),
				saveEmployeeEvent.getDepartment(),
				jwtService.extractUsername(getToken()),1);
		
		employeeHistoryRepo.save(employeeHistory);
	}
	
	@EventListener
	@Transactional
	public void handle(HistoryEmployeeDelete historyEmployee) {
		int version = employeeHistoryRepo.findMaxVersionByEmployeeId(historyEmployee.getEmployee().getId());
		
		System.out.println("version : " + version);
		
		String email = jwtService.extractUsername(getToken());
		
		if(historyEmployee.getMessage().equals("Delete")) {
			email = email + " "+historyEmployee.getMessage();
		}
		EmployeeHistory employeeHistory = new EmployeeHistory(
							historyEmployee.getEmployee(),
							historyEmployee.getEmployee().getPosition_id(),
							historyEmployee.getEmployee().getDepartment(),
							email,
							version + 1
							);
		
		employeeHistoryRepo.save(employeeHistory);
	}	
	
	private String getToken() {
		
		String header = httpServletRequest.getHeader("Authorization");
		
		if(header != null || header.startsWith("Bearer ")) {
			String token = header.substring(7);
			
			return token;
		}else {
			throw new ApiBusinessException("There is no token in request");
		}
		
	}

	
	
}
