package com.sea.challenge.register.validators;

import com.sea.challenge.register.validators.impl.CEPValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CEPValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CEP {
    String message() default "invalid cep";

    boolean mask() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
