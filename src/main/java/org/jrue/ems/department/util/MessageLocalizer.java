package org.jrue.ems.department.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageLocalizer {
	

	@Autowired
	private MessageSource messageSource;
	
	public String localizeMessage(String messageKey) {
		String message = null;
		if (messageKey != null) {
			message = messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
		}
		return message;
	}
}
