package com.home.policies.dto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.home.policies.core.exception.IdDoesNotMatch;
import com.home.policies.core.exception.NotValidInputData;
import com.home.policies.dto.PolicyDto;

@Component
public class PolicyDtoValidator {
	
	private MessageSource messageSource;
	
	@Autowired
	public PolicyDtoValidator(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public void validatePost(PolicyDto policyDto) {
		if(policyDto.getProduct() == null || policyDto.getTaker() == null
				|| policyDto.getVehicle() == null) {
			throw new NotValidInputData(messageSource);
		}
	}
	
	public void validatePut(PolicyDto policyDto, Long idPolicy) {
		if(policyDto.getProduct() == null || policyDto.getTaker() == null
				|| policyDto.getVehicle() == null) {
			throw new NotValidInputData(messageSource);
		}else if(policyDto.getId() != idPolicy){
			throw new IdDoesNotMatch(messageSource);
		}
	}
	
}
