package com.example.exabonus.config.anotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidatorTwo implements ConstraintValidator<EmailCustom, String> {

    @Override
    public void initialize(EmailCustom constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern pattern = Pattern.compile("^(?:(?!.*?[.]{2})[a-zA-Z0-9](?:[a-zA-Z0-9.+!%-]{1,64}|)|\\\"[a-zA-Z0-9.+!% -]{1,64}\\\")@[a-zA-Z0-9][a-zA-Z0-9.-]+(.[a-z]{2,}|.[0-9]{1,})$");
        return pattern.matcher(value).matches();
    }
}
