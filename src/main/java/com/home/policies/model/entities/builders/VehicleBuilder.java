package com.home.policies.model.entities.builders;

import com.home.policies.model.entities.Vehicle;

public class VehicleBuilder {
	
	private Long id;
	private String brand;
	private String model;
	private String color;
	private String licensePlate;
	
	public VehicleBuilder() {
		this.brand=new String();
		this.model=new String();
		this.color=new String();
	}
	
	public VehicleBuilder withId(Long id) {
		this.id=id;
		return this;
	}
	
	public VehicleBuilder withBrand(String brand) {
		this.brand=brand;
		return this;
	}
	public VehicleBuilder withModel(String model) {
		this.model=model;
		return this;
	}
	
	public VehicleBuilder withColor(String color) {
		this.color=color;
		return this;
	}
	
	public VehicleBuilder withLicensePlate(String licensePlate) {
		this.licensePlate=licensePlate;
		return this;
	}
	
	public Vehicle build() {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(brand);
		vehicle.setColor(color);
		vehicle.setId(id);
		vehicle.setLicensePlate(licensePlate);
		vehicle.setModel(model);
		return vehicle;
	}
}
