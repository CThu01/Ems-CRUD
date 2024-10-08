//package com.ems.backend.model.dto.mapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.ems.backend.model.dto.request.MyUserAccountRegister;
//import com.ems.backend.model.dto.response.MyUserAccountRegisterRepsonse;
//import com.ems.backend.model.entity.MyUserAccount;
//import com.ems.backend.model.repo.EmployeeRepo;
//import com.ems.backend.util.exception.ApiBusinessException;
//
//@Service
//public class MyUserAccountMapper {
//	
//	@Autowired
//	private EmployeeRepo employeeRepo;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	public MyUserAccountMapper(EmployeeRepo employeeRepo) {
//		this.employeeRepo = employeeRepo;
//	}
//
//	public MyUserAccount mapToMyUserAccount(MyUserAccountRegister userDto) {
//		return new MyUserAccount(
//					passwordEncoder.encode(userDto.getPassword()),
//					userDto.getEmail(),
//					userDto.getActive(),
//					userDto.getRole(),
//					
//					// need refactoring here
//					employeeRepo.findById(userDto.getEmplyeeId())   
//								.orElseThrow(() -> new ApiBusinessException("Employee not found"))
//				);
//	}
//	
//	public MyUserAccountRegisterRepsonse mapToMyUserAccountDtoResponse(MyUserAccount myUserAccount) {
//		return new MyUserAccountRegisterRepsonse(
//					myUserAccount.getEmail(),
//					myUserAccount.getActive(),
//					myUserAccount.getRole()
//				);
//	}
//}
