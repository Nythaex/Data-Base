package com.example.exa.util;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidatorUtilImpl implements ValidatorUtil {
    private final Validator validator;

    public ValidatorUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean validate(T entity) {
        return validator.validate(entity).isEmpty();
    }
}
