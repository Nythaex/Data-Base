package com.example.exabonus.config.anotation;


import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Invalid password Address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minLength() default  Integer.MIN_VALUE;
    int maxLength() default  Integer.MAX_VALUE;
   boolean containsDigits() default true;
    boolean containsLowercase() default true;
    boolean containsUppercase() default true;
    boolean containsSpecialSymbol() default true;

}


