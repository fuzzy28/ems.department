package org.jrue.ems.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageLocalizerServiceBean implements MessageLocalizerService {

    @Autowired
    private MessageSource messageSource;

    public String localizeMessage(String messageKey) {
	String message = null;
	if (messageKey != null) {
	    message = messageSource
		    .getMessage(messageKey, null, LocaleContextHolder.getLocale());
	}
	return message;
    }
}
