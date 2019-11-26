package com.home.policies.dto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.home.policies.core.exception.IdDoesNotMatch;
import com.home.policies.core.exception.NotValidInputData;
import com.home.policies.dto.VehicleDto;

@Component
public class VehicleDtoValidator {
private MessageSource messageSource;
	
	@Autowired
	public VehicleDtoValidator(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public void validatePut(VehicleDto vehicleDto, Long idVehicle){
		if(vehicleDto.getLicensePlate() == null || vehicleDto.getId() == null) {
			throw new NotValidInputData(messageSource);
		}else if(vehicleDto.getId() != idVehicle) {
			throw new IdDoesNotMatch(messageSource);
		}
	}
	
}
