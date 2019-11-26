package com.home.policies.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class NotValidInputData extends RuntimeException{
	private static final long serialVersionUID = 3L;
	private static final String errorCode = "003";
	
	public NotValidInputData(MessageSource messageSource) {
		super(messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale()));
	}
}
