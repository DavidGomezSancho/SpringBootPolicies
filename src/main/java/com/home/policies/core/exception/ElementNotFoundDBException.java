package com.home.policies.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class ElementNotFoundDBException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final String errorCode = "001";
	
	public ElementNotFoundDBException(MessageSource messageSource) {
		super(messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale()));
	}
}
