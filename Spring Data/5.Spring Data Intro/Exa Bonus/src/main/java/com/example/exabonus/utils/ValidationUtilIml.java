package com.example.exabonus.utils;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Component
public class ValidationUtilIml implements ValidationUtil{
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator=factory.getValidator();

    public <E> boolean isValid(E object) {
        return validator.validate(object).isEmpty();
    }
}
