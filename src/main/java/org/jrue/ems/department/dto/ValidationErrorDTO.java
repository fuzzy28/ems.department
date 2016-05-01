package org.jrue.ems.department.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO {

    private final List<FieldErrorDTO> fieldErrors;

    public ValidationErrorDTO() {
	fieldErrors = new ArrayList<>();
    }

    public void addFieldError(FieldErrorDTO fieldErrorDto) {
	fieldErrors.add(fieldErrorDto);
    }

    public List<FieldErrorDTO> getFieldErrors() {
	return fieldErrors;
    }
}
