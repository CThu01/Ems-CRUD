package com.ems.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.backend.model.dto.request.PositionDto;
import com.ems.backend.model.response.ApiResponse;
import com.ems.backend.service.PositionService;

import jakarta.websocket.server.PathParam;

@CrossOrigin("*")
@RestController
@RequestMapping("position")
public class PositionApi {

	@Autowired
	private PositionService positionService;
	
	@PostMapping
	public ApiResponse<String> createPosition(@Validated @RequestBody PositionDto positionDto,BindingResult result){
		return ApiResponse.success(positionService.createPosition(positionDto));
	}
	
	@GetMapping
	public ApiResponse<List<PositionDto>> getAllPosition(){
		return ApiResponse.success(positionService.getAllPosition());
	}
	
	@PutMapping("{id}")
	public ApiResponse<String> updatePosition(@PathVariable("id") int id,@Validated @RequestBody PositionDto positionDto,BindingResult result){
		return ApiResponse.success(positionService.updatePosition(id, positionDto));
	}
	
	@DeleteMapping("{id}")
	public ApiResponse<String> deletePosition(@PathVariable("id") int id){
		return ApiResponse.success(positionService.deletePosition(id));
	}
}









