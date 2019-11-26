package com.home.policies.service;

import java.util.Collection;

import com.home.policies.dto.VehicleDto;
import com.home.policies.model.entities.Vehicle;

public interface VehicleService {
	public Vehicle findOrCreateVehicle(VehicleDto vehicleDto);
	public Collection<VehicleDto> getVehiclesByTaker(String takerEmail);
	public Collection<VehicleDto> getAllVehicleWithPolicy();
	public Vehicle getOneVehicle(Long idVehicle);
	
	public Collection<VehicleDto> getAllVehicle();
	public VehicleDto getVehicle(Long idVehicle);
	public VehicleDto createVehicle(VehicleDto vehicleDto);
	public VehicleDto updateVehicle(VehicleDto vehicleDto, Long idVehicle);
	public void deleteVehicle(Long idVehicle);
	
}
