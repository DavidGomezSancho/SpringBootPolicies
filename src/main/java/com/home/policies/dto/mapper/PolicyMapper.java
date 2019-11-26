package com.home.policies.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.home.policies.dto.PolicyDto;
import com.home.policies.dto.builders.PolicyDtoBuilder;
import com.home.policies.model.entities.Policy;
import com.home.policies.model.entities.builders.PolicyBuilder;

@Component
public class PolicyMapper {
	
	private TakerMapper takerMapper;
	private VehicleMapper vehicleMapper;
	
	@Autowired
	public PolicyMapper(TakerMapper takerMapper, VehicleMapper vehicleMapper) {
		this.takerMapper=takerMapper;
		this.vehicleMapper=vehicleMapper;
	}
	
	public PolicyDto toDtoFromEntity(Policy policy){
		return new PolicyDtoBuilder()
				.withId(policy.getId())
				.withFinishContractDate(policy.getFinishContractDate())
				.withPremium(policy.getPremium())
				.withProduct(policy.getProduct())
				.withSignedDate(policy.getSignedDate())
				.withTaker(takerMapper.toDtoFromEntity(policy.getTaker()))
				.withVehicle(vehicleMapper.toDtoFromEntity(policy.getVehicle()))
				.build();
	}
	
	public Policy toEntityFromDtoWithoutId(PolicyDto policyDto) {
		return new PolicyBuilder()
				.withFinishContractDate(policyDto.getFinishContractDate())
				.withPremium(policyDto.getPremium())
				.withProduct(policyDto.getProduct())
				.withSignedDate(policyDto.getSignedDate())
				.withTaker(takerMapper.toEntityFromDtoWithoutId(policyDto.getTaker()))
				.withVehicle(vehicleMapper.toEntityFromDtoWithoutId(policyDto.getVehicle()))
				.build();
	}
	
	public Policy toEntityFromDtoWithId(PolicyDto policyDto) {
		return new PolicyBuilder()
				.withId(policyDto.getId())
				.withFinishContractDate(policyDto.getFinishContractDate())
				.withPremium(policyDto.getPremium())
				.withProduct(policyDto.getProduct())
				.withSignedDate(policyDto.getSignedDate())
				.withTaker(takerMapper.toEntityFromDtoWithId(policyDto.getTaker()))
				.withVehicle(vehicleMapper.toEntityFromDtoWithId(policyDto.getVehicle()))
				.build();
	}
}
