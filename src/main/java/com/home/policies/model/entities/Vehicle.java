package com.home.policies.model.entities;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Vehicle {
	@Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String brand;
	
	private String model;
	
	private String color;
	
	@NotBlank
	private String licensePlate;
	
	@OneToOne(mappedBy = "vehicle", fetch = FetchType.LAZY)
	private Policy policy;

	public Optional<Policy> getPolicy() {
		return Optional.ofNullable(policy);
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Optional<String> getBrand() {
		return Optional.ofNullable(brand);
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Optional<String> getModel() {
		return Optional.ofNullable(model);
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Optional<String> getColor() {
		return Optional.ofNullable(color);
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, color, id, licensePlate, model);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}
	
	
}
