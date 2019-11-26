package com.home.policies.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class VehicleWithPolicyException extends RuntimeException{
	private static final long serialVersionUID = 2L;
	private static final String errorCode = "002";
	
	public VehicleWithPolicyException(MessageSource messageSource, String licensePlate) {
		super(messageSource.getMessage(errorCode, createObjectString(licensePlate), LocaleContextHolder.getLocale()));
	}
	
	private static String[] createObjectString(String licensePlate) {
		String licensesPlates[] = new String[1];
		licensesPlates[0] = licensePlate;
		return licensesPlates;
	}
}
