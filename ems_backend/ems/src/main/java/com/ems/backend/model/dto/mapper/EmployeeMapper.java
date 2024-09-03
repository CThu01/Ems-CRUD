package com.ems.backend.model.dto.mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.request.EmployeeDto;
import com.ems.backend.model.dto.response.EmployeeDtoResponse;
import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.entity.Position;
import com.ems.backend.model.repo.DepartmentRepo;
import com.ems.backend.model.repo.EmployeeRepo;
import com.ems.backend.model.repo.PositionRepo;
import com.ems.backend.util.exception.ApiBusinessException;

@Service
public class EmployeeMapper {

	private DepartmentRepo departmentRepo;
	private PositionRepo positionRepo;
	
	@Value("${ems.file.folder}")
	private String fileDirectory;
	
	@Autowired
	public EmployeeMapper(DepartmentRepo departmentRepo,PositionRepo positionRepo) {
		this.departmentRepo = departmentRepo;
		this.positionRepo = positionRepo;
	}
	
	public Employee mapToEmployee(EmployeeDto employeeDto) {
		
		
		return new Employee(
					employeeDto.getUsername(),
					employeeDto.getSalary(),
					employeeDto.getAddress(),
					employeeDto.getPassport(),
					new Date().getTime(),
					Long.valueOf(employeeDto.getDob()),
					employeeDto.getImage(),
					departmentRepo.findById(employeeDto.getDepartment()).orElseThrow(() -> new ApiBusinessException(null)),
					positionRepo.findById(employeeDto.getPosition()).orElseThrow(() -> new ApiBusinessException(null))
				);  
	}
	
	public EmployeeDtoResponse mapToEmployeeDtoResponse(Employee employee) {
		
		Map<String, String> allImage = new HashMap<String, String>();
		Path employeeDirPath = Path.of(fileDirectory + "employee_" + String.valueOf(employee.getId()));
		
		if(Files.exists(employeeDirPath) && Files.isDirectory(employeeDirPath)) {
			 
			try {
				Stream<Path> streamPath = Files.list(employeeDirPath);
				allImage =  streamPath
						.filter(Files::isRegularFile)
						.collect(Collectors.toMap(
								path -> path.getFileName().toString(),
								path -> encodedFileToBase64(path)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return new EmployeeDtoResponse(
					employee.getUsername(),
					employee.getSalary().toString(),
					employee.getAddress(),
					employee.getPassport(),
					employee.getDob().toString(),
					allImage,
					employee.getDepartment().getName(),
					employee.getPosition_id().getName()
				);
	}
	
	private String encodedFileToBase64(Path path) {
		
		byte[] fileContent = null;
		try {
			fileContent = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(fileContent);
	}
}



