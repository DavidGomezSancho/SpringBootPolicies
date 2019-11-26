package com.home.policies.dto.builders;

import java.time.LocalDate;

import com.home.policies.dto.PolicyDto;
import com.home.policies.dto.TakerDto;
import com.home.policies.dto.VehicleDto;

public class PolicyDtoBuilder {
	
	private Long id;
	private String product;
	private Long premium;
	private LocalDate signedDate;
	private LocalDate finishContractDate;
	private TakerDto taker;
	private VehicleDto vehicle;
	
	public PolicyDtoBuilder() {
		this.product = new String();
		this.premium = new Long(0);
	}
	
	public PolicyDtoBuilder withId(Long id) {
		this.id=id;
		return this;
	}
	
	public PolicyDtoBuilder withProduct(String product) {
		this.product=product;
		return this;
	}
	
	public PolicyDtoBuilder withPremium(Long premium) {
		this.premium=premium;
		return this;
	}
	
	public PolicyDtoBuilder withSignedDate(LocalDate signedDate) {
		this.signedDate=signedDate;
		return this;
	}
	
	public PolicyDtoBuilder withFinishContractDate(LocalDate finishContractDate) {
		this.finishContractDate=finishContractDate;
		return this;
	}
	
	public PolicyDtoBuilder withTaker(TakerDto taker) {
		this.taker=taker;
		return this;
	}
	
	public PolicyDtoBuilder withVehicle(VehicleDto vehicle) {
		this.vehicle=vehicle;
		return this;
	}
	
	public PolicyDto build() {
		PolicyDto policyDto = new PolicyDto();
		policyDto.setFinishContractDate(finishContractDate);
		policyDto.setPremium(premium);
		policyDto.setProduct(product);
		policyDto.setSignedDate(signedDate);
		policyDto.setTaker(taker);
		policyDto.setVehicle(vehicle);
		policyDto.setId(id);
		return policyDto;
	}
	
}
