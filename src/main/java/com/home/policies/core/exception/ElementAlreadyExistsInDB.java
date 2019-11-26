package com.home.policies.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class ElementAlreadyExistsInDB extends RuntimeException{

	private static final long serialVersionUID = 1917559963583782426L;
	private static final String errorCode = "005";
	
	public ElementAlreadyExistsInDB(MessageSource messageSource) {
		super(messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale()));
	}
	
}
