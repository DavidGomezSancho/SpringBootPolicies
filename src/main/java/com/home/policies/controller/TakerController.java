package com.home.policies.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.home.policies.dto.TakerDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Taker"})
public interface TakerController {
	
	@ApiOperation(value = "Update Taker", 
			notes = "Update a taker from a TakerDto")
	public ResponseEntity<Void> updateTaker(TakerDto takerDto, Long idTaker);
	
	@ApiOperation(value = "Get Taker", 
			notes = "Get a taker from an id")
	public TakerDto getTaker(Long idTaker);
	
	@ApiOperation(value = "Get All Takers", 
			notes = "Get all takers from the database")
	public Collection<TakerDto> getAllTaker();
	
	@ApiOperation(value = "Create Taker", 
			notes = "Create a new taker from a TakerDto")
	public ResponseEntity<Void> createTaker(TakerDto takerDto);
	
	@ApiOperation(value = "Delete Taker", 
			notes = "Delete a taker from a idTaker")
	public ResponseEntity<Void> deleteTaker(Long idTaker);
}
