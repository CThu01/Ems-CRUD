package com.ems.backend.model.dto.mapper;

import com.ems.backend.model.dto.request.PositionDto;
import com.ems.backend.model.entity.Position;

public class PositionMapper {

	public static PositionDto maptoPositoinDto(Position position) {
		return new PositionDto(position.getName());
	}
	
	public static Position maptoPosition(PositionDto positionDto) {
		return new Position( positionDto.getName());
	}
}
