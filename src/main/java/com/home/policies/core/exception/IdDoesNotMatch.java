package com.home.policies.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class IdDoesNotMatch extends RuntimeException{
	private static final long serialVersionUID = -2266974048425619705L;
	private static final String errorCode = "004";
	
	public IdDoesNotMatch(MessageSource messageSource) {
		super(messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale()));
	}
}
