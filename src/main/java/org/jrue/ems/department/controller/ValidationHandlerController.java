package org.jrue.ems.department.controller;

import org.jrue.ems.department.dto.FieldErrorDTO;
import org.jrue.ems.department.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationHandlerController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidation(MethodArgumentNotValidException ex) {
	ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
	for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	    FieldErrorDTO fe = new FieldErrorDTO(
		    fieldError.getField(),
		    localizeMessage(fieldError));
	    validationErrorDTO.addFieldError(fe);
	}
	return validationErrorDTO;
    }

    private String localizeMessage(FieldError fieldError) {
	String message = null;
	if (fieldError != null) {
	    message = messageSource.getMessage(
		    fieldError.getDefaultMessage(),
		    null,
		    LocaleContextHolder.getLocale());
	}
	return message;
    }
}
