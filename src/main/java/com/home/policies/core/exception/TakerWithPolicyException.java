package com.home.policies.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class TakerWithPolicyException extends RuntimeException{

	private static final long serialVersionUID = -7194879326833598173L;
	private static final String errorCode = "006";
	
	public TakerWithPolicyException(MessageSource messageSource) {
		super(messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale()));
	}
}
