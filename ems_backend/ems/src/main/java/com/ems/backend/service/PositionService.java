package com.ems.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.request.PositionDto;

@Service
public interface PositionService {

	String createPosition(PositionDto positionDto);
	
	List<PositionDto> getAllPosition();
	
	String updatePosition(int id,PositionDto positionDto);
	
	String deletePosition(int id);
}
