package com.ems.backend.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ems.backend.util.exception.ApiBusinessException;

@Service
public class FileService {

	@Value("${ems.file.folder}")
	private String fileDirectory;
	
	private static final String FILE_FORMAT = "p_%06d_%s_%03d.%s";
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	public String save(int id,MultipartFile photo) throws IOException {
		
		String employeeDirectory = fileDirectory + "employee_" + String.valueOf(id);
		
		Path employeeDirPath = Path.of(employeeDirectory);
		if(!Files.exists(employeeDirPath)) {
			Files.createDirectories(employeeDirPath);
		}
		
		Stream<Path> countFile = Files.list(employeeDirPath);
		String fileName = getEmployeeFileName(id, photo,(int)countFile.count());
		Path fileFullPath = Paths.get(employeeDirectory, fileName);
		Files.write(fileFullPath, photo.getBytes());
		
		return fileFullPath.toString();
	}
	
	public void deleteFiles(int employeeId) throws IOException {
		
		String employeeDirectory = fileDirectory + "employee_" + String.valueOf(employeeId);
		Path directoryPath = Path.of(employeeDirectory);
		if(Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
			
			Stream<Path> traverseInFolder = Files.walk(directoryPath);
			traverseInFolder
						.sorted(Comparator.reverseOrder())
						.forEach(
								path -> 
									{
										try {
											Files.delete(path);
										} catch (IOException e) {
											throw new ApiBusinessException(path + " cann't be deleted");
										}
									}
									
						);
		}
		
	}
	
	private String getEmployeeFileName(int id,MultipartFile photo,int countFile) {
		
		var array = photo.getOriginalFilename().split("\\.");
		String extension = array[array.length - 1];
		
		return FILE_FORMAT.formatted(id,LocalDateTime.now().format(DF),countFile+1,extension);
	}
}



