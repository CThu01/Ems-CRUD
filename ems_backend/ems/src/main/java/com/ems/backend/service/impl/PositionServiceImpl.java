package com.ems.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.backend.model.dto.mapper.PositionMapper;
import com.ems.backend.model.dto.request.PositionDto;
import com.ems.backend.model.entity.Position;
import com.ems.backend.model.repo.PositionRepo;
import com.ems.backend.service.PositionService;
import com.ems.backend.util.exception.ApiBusinessException;

@Service
public class PositionServiceImpl implements PositionService{

	@Autowired
	private PositionRepo positionRepo;
	
	@Override
	public String createPosition(PositionDto positionDto) {
		
		Position position = PositionMapper.maptoPosition(positionDto);
		
		Position createdPosition = positionRepo.save(position);
		
		return createdPosition.getName() + " is created successfully";
	}

	@Override
	public String updatePosition(int id, PositionDto positionDto) {
		
		Position position = positionRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Position doesn't exit"));
		
		position.setName(positionDto.getName());
		
		Position updatedPosition = positionRepo.save(position);
		
		return updatedPosition.getName() + " is updated successfully";
	}

	@Override
	public String deletePosition(int id) {
		
		Position position = positionRepo.findById(id).orElseThrow(() -> new ApiBusinessException("Position doesn't exit"));
		
		positionRepo.delete(position);
		
		return "Position id: " + id + " has been deleted successfully";
	}

	@Override
	public List<PositionDto> getAllPosition() {
		
		List<Position> positionList = positionRepo.findAll();
		
		List<PositionDto> positionDtoList = new ArrayList<PositionDto>();
		
		for(Position p : positionList) {
			positionDtoList.add(PositionMapper.maptoPositoinDto(p));
		}
		
		return positionDtoList;
	}

}
