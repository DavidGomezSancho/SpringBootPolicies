package com.home.policies.dto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.home.policies.core.exception.IdDoesNotMatch;
import com.home.policies.core.exception.NotValidInputData;
import com.home.policies.dto.TakerDto;

@Component
public class TakerDtoValidator {
	private MessageSource messageSource;
	
	@Autowired
	public TakerDtoValidator(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public void validatePut(TakerDto takerDto, Long idTaker) {
		if(takerDto.getName() == null || takerDto.getSurname1() == null
				|| takerDto.getTelephone() == null || takerDto.getId() == null
				|| takerDto.getEmail() == null) {
			throw new NotValidInputData(messageSource);
		}else if(takerDto.getId()!=idTaker) {
			throw new IdDoesNotMatch(messageSource);
		}
	}
}
