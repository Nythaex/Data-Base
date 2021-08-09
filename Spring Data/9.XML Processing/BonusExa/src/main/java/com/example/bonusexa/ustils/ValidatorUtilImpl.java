package com.example.bonusexa.ustils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorUtilImpl implements ValidatorUtil {
   private final  Validator validator;

    public ValidatorUtilImpl() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> Boolean isEmpty(T entity) {
        return validator.validate(entity).isEmpty();
    }
}
