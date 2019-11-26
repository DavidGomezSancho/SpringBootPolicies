package com.home.policies.dto.builders;

import com.home.policies.dto.VehicleDto;

public class VehicleDtoBuilder {

	private Long id;
	private String brand;
	private String model;
	private String color;
	private String licensePlate;
	
	public VehicleDtoBuilder() {
		this.brand=new String();
		this.model=new String();
		this.color=new String();
		this.licensePlate=new String();
	}
	
	public VehicleDtoBuilder withId(Long id) {
		this.id=id;
		return this;
	}
	
	public VehicleDtoBuilder withBrand(String brand) {
		this.brand=brand;
		return this;
	}
	public VehicleDtoBuilder withModel(String model) {
		this.model=model;
		return this;
	}
	
	public VehicleDtoBuilder withColor(String color) {
		this.color=color;
		return this;
	}
	
	public VehicleDtoBuilder withLicensePlate(String licensePlate) {
		this.licensePlate=licensePlate;
		return this;
	}
	
	public VehicleDto build() {
		VehicleDto vehicleDto = new VehicleDto();
		vehicleDto.setBrand(brand);
		vehicleDto.setColor(color);
		vehicleDto.setLicensePlate(licensePlate);
		vehicleDto.setModel(model);
		vehicleDto.setId(id);
		return vehicleDto;
	}
	
}
