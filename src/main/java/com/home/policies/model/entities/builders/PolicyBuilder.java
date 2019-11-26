package com.home.policies.model.entities.builders;

import java.time.LocalDate;

import com.home.policies.model.entities.Policy;
import com.home.policies.model.entities.Taker;
import com.home.policies.model.entities.Vehicle;

public class PolicyBuilder {
	
	private Long id;
	private String product;
	private Long premium;
	private LocalDate signedDate;
	private LocalDate finishContractDate;
	private Taker taker;
	private Vehicle vehicle;
	
	public PolicyBuilder() {
		this.product = new String();
		this.premium = new Long(0);
	}
	
	public PolicyBuilder withId(Long id) {
		this.id=id;
		return this;
	}
	
	public PolicyBuilder withProduct(String product) {
		this.product=product;
		return this;
	}
	
	public PolicyBuilder withPremium(Long premium) {
		this.premium=premium;
		return this;
	}
	
	public PolicyBuilder withSignedDate(LocalDate signedDate) {
		this.signedDate=signedDate;
		return this;
	}
	
	public PolicyBuilder withFinishContractDate(LocalDate finishContractDate) {
		this.finishContractDate=finishContractDate;
		return this;
	}
	
	public PolicyBuilder withTaker(Taker taker) {
		this.taker=taker;
		return this;
	}
	
	public PolicyBuilder withVehicle(Vehicle vehicle) {
		this.vehicle=vehicle;
		return this;
	}
	
	public Policy build() {
		Policy policy = new Policy();
		policy.setId(id);
		policy.setFinishContractDate(finishContractDate);
		policy.setPremium(premium);
		policy.setProduct(product);
		policy.setSignedDate(signedDate);
		policy.setTaker(taker);
		policy.setVehicle(vehicle);
		return policy;
	}
}
