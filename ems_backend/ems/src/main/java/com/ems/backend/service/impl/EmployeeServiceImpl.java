package com.ems.backend.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ems.backend.model.dto.mapper.EmployeeMapper;
import com.ems.backend.model.dto.request.EmployeeDto;
import com.ems.backend.model.dto.request.HistoryEmployeeDelete;
import com.ems.backend.model.dto.request.HistoryEmployeeCreate;
import com.ems.backend.model.dto.response.DynamicSearchDto;
import com.ems.backend.model.dto.response.EmployeeDtoResponse;
import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.repo.DepartmentRepo;
import com.ems.backend.model.repo.EmployeeRepo;
import com.ems.backend.model.repo.PositionRepo;
import com.ems.backend.model.repo.UserRepo;
//import com.ems.backend.security.jwt.JwtService;
import com.ems.backend.service.EmployeeService;
import com.ems.backend.util.exception.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private PositionRepo positionRepo;
	
	@Autowired
	private UserRepo userRepo;
		
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public EmployeeDtoResponse create(EmployeeDto employeeDto,MultipartFile photo) throws IOException {
		Employee employee = employeeMapper.mapToEmployee(employeeDto);
		
		Employee createdEmployee = employeeRepo.save(employee);
		
		String fullFilePath = fileService.save(createdEmployee.getId(), photo);
		
		createdEmployee.setImage(fullFilePath);
		
		// For history record
		String email = userRepo.findByEmployeeId(createdEmployee.getId());
		eventPublisher.publishEvent(new HistoryEmployeeCreate(
												createdEmployee,
												createdEmployee.getPosition_id(),
												createdEmployee.getDepartment(),
												email
											));
		
		return employeeMapper.mapToEmployeeDtoResponse(createdEmployee);
	}
	

	@Override
	public EmployeeDtoResponse getEmployeeById(int id) {

		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Employee doesn't exit"));
		
//		mapper.map(employee, EmployeeDtoResponse.class);
		return mapper.map(employee, EmployeeDtoResponse.class);
	}

	@Override
	public List<EmployeeDtoResponse> getAllEmployee() {
			
		List<Employee> employeeList = employeeRepo.findAll();
		
		List<EmployeeDtoResponse> employeeDtoList = new ArrayList<EmployeeDtoResponse>();
	
		System.out.println(employeeDtoList);
		for(Employee employee : employeeList) {
			employeeDtoList.add(employeeMapper.mapToEmployeeDtoResponse(employee));
		}
		
		return employeeDtoList;
	}

	@Override
	public EmployeeDtoResponse updateEmployee(int id, EmployeeDto employeeDto,MultipartFile photo) throws IOException {

		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Employee doesn't exit"));
		
		String fullFilePath = fileService.save(employee.getId(), photo);
		
		employee.setUsername(employeeDto.getUsername());
		employee.setSalary(employeeDto.getSalary());
		employee.setAddress(employeeDto.getAddress());
		employee.setPassport(employeeDto.getPassport());
		employee.setStartDate(new Date().getTime());
		employee.setDob(Long.valueOf(employeeDto.getDob()));
		employee.setImage(fullFilePath);
		employee.setDepartment(
				departmentRepo.findById(employeeDto.getDepartment())
				.orElseThrow(() -> new ApiBusinessException("Department doesn't exit")));
		employee.setPosition_id(
				positionRepo.findById(employeeDto.getPosition())
				.orElseThrow(() -> new ApiBusinessException("Positino doesn't exit")));

		
		Employee updatedEmployee = employeeRepo.save(employee);
		
		// record history
		eventPublisher.publishEvent(new HistoryEmployeeDelete(updatedEmployee,""));
		
		return employeeMapper.mapToEmployeeDtoResponse(updatedEmployee);
	}

	@Override
	public String deleteEmployee(int id) {
		
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Employee doesn't exit"));
		
		// record delete
		eventPublisher.publishEvent(new HistoryEmployeeDelete(employee,"Delete"));
		
		employeeRepo.delete(employee);
		
		try {
			fileService.deleteFiles(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "Employee id: " + id + " have been deleted successfully";
	}

	@Override
	public List<DynamicSearchDto> search(String keyword) {
		
		Function<CriteriaBuilder, CriteriaQuery<DynamicSearchDto>> queryFun = 
					cb -> {
						CriteriaQuery<DynamicSearchDto> cq = cb.createQuery(DynamicSearchDto.class);	
						Root<Employee> root = cq.from(Employee.class);
						DynamicSearchDto.joinTable(root);
						DynamicSearchDto.projection(cq,cb,root);
						cq.where(cb.or(DynamicSearchDto.predicates(cb,keyword,root)));
						return cq;
					};
		
		return employeeRepo.findAll(queryFun);
	}



	
}





