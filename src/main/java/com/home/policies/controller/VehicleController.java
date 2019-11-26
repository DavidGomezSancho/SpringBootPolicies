package com.home.policies.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.home.policies.dto.VehicleDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Vehicle"})
public interface VehicleController {
	
	@ApiOperation(value = "Get Vehicles By Taker email", 
			notes = "Return are all vehicles of one taker")
	public Collection<VehicleDto> getVehiclesByTaker(String takerEmail);
	
	@ApiOperation(value = "Get All Vehicles With a Policy", 
			notes = "Return are all vehicles assigned to a policy and ordered by license plate")
	public Collection<VehicleDto> getAllVehicleWithPolicy();
	
	@ApiOperation(value = "Create Vehicle", 
			notes = "Create a new vehicle on database")
	public ResponseEntity<Void> createVehicle(VehicleDto vehicle);
	
	@ApiOperation(value = "Get all Vehicles", 
			notes = "Get all vehicles in database")
	public Collection<VehicleDto> getAllVehicle();
	
	@ApiOperation(value = "Get a Vehicle", 
			notes = "Get a vehicle by id")
	public VehicleDto getVehicle(Long idVehicle);
	
	@ApiOperation(value = "Update Vehicle", 
			notes = "Update vehicle by id")
	public ResponseEntity<Void> updateVehicle(VehicleDto vehicleDto, Long idVehicle);
	
	@ApiOperation(value = "Delete Vehicle", 
			notes = "Delete vehicle by id")
	public ResponseEntity<Void> deleteVehicle(Long idVehicle);
}
