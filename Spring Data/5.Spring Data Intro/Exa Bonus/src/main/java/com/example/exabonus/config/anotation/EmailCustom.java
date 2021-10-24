package com.example.exabonus.config.anotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidatorTwo.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailCustom {
    String message() default "Invalid Email Address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}


