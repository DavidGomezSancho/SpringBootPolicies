package com.home.policies.model.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Policy {
	
	@Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String product;
	
	@NotNull
	private Long premium;
	
	@NotNull
	private LocalDate signedDate;
	
	@NotNull
	private LocalDate finishContractDate;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Taker taker;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Vehicle vehicle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Long getPremium() {
		return premium;
	}

	public void setPremium(Long premium) {
		this.premium = premium;
	}

	public LocalDate getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(LocalDate signedDate) {
		this.signedDate = signedDate;
	}

	public LocalDate getFinishContractDate() {
		return finishContractDate;
	}

	public void setFinishContractDate(LocalDate finishContractDate) {
		this.finishContractDate = finishContractDate;
	}

	public Taker getTaker() {
		return taker;
	}

	public void setTaker(Taker taker) {
		this.taker = taker;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(finishContractDate, id, premium, product, signedDate, taker, vehicle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Policy other = (Policy) obj;
		if (finishContractDate == null) {
			if (other.finishContractDate != null)
				return false;
		} else if (!finishContractDate.equals(other.finishContractDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (premium == null) {
			if (other.premium != null)
				return false;
		} else if (!premium.equals(other.premium))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (signedDate == null) {
			if (other.signedDate != null)
				return false;
		} else if (!signedDate.equals(other.signedDate))
			return false;
		if (taker == null) {
			if (other.taker != null)
				return false;
		} else if (!taker.equals(other.taker))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}
	
	
}
