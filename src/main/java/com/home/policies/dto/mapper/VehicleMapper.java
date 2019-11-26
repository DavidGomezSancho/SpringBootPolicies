package com.home.policies.dto.mapper;

import org.springframework.stereotype.Component;

import com.home.policies.dto.VehicleDto;
import com.home.policies.dto.builders.VehicleDtoBuilder;
import com.home.policies.model.entities.Vehicle;
import com.home.policies.model.entities.builders.VehicleBuilder;

@Component
public class VehicleMapper {
	
	public VehicleDto toDtoFromEntity(Vehicle vehicle) {
		return new VehicleDtoBuilder()
				.withId(vehicle.getId())
				.withBrand(vehicle.getBrand().orElseGet(()->""))
				.withColor(vehicle.getColor().orElseGet(()->""))
				.withLicensePlate(vehicle.getLicensePlate())
				.withModel(vehicle.getModel().orElseGet(()->""))
				.build();
	}
	
	public Vehicle toEntityFromDtoWithoutId(VehicleDto vehicleDto) {
		return new VehicleBuilder()
				.withBrand(vehicleDto.getBrand())
				.withColor(vehicleDto.getColor())
				.withLicensePlate(vehicleDto.getLicensePlate())
				.withModel(vehicleDto.getModel())
				.build();
	}
	
	public Vehicle toEntityFromDtoWithId(VehicleDto vehicleDto) {
		return new VehicleBuilder()
				.withId(vehicleDto.getId())
				.withBrand(vehicleDto.getBrand())
				.withColor(vehicleDto.getColor())
				.withLicensePlate(vehicleDto.getLicensePlate())
				.withModel(vehicleDto.getModel())
				.build();
	}
	
}
