package com.example.exabonus.config.anotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private Password passwordAnnotation;

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        passwordAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!passwordAnnotation.containsDigits()) {
            for (int i = 0; i < value.length(); i++) {
                if (Character.isDigit(value.charAt(i))) {
                    return false;
                }
            }
        }

        if (!passwordAnnotation.containsLowercase()) {
            for (int i = 0; i < value.length(); i++) {
                if (Character.isLowerCase(value.charAt(i))) {
                    return false;
                }
            }
        }

        if (!passwordAnnotation.containsUppercase()) {
            for (int i = 0; i < value.length(); i++) {
                if (Character.isUpperCase(value.charAt(i))) {
                    return false;
                }
            }
        }
        if (!passwordAnnotation.containsSpecialSymbol()) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            if (!pattern.matcher(value).matches()) {
                return false;
            }
        }
        if (value.length() < passwordAnnotation.minLength()) {
            return false;
        }
        return value.length() <= passwordAnnotation.maxLength();
    }
}
