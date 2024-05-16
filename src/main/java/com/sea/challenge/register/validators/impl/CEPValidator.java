package com.sea.challenge.register.validators.impl;

import com.sea.challenge.register.exceptions.viacep.InvalidCepException;
import com.sea.challenge.register.validators.CEP;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEPValidator implements ConstraintValidator<CEP, String> {

    private boolean mask;

    @Override
    public void initialize(CEP constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.mask = constraintAnnotation.mask();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern pattern = mask ?
                Pattern.compile("^[0-9]{2}\\.[0-9]{3}-[0-9]{3}$") :
                Pattern.compile("^[0-9]{8}$");

        Matcher matcher = pattern.matcher(value);

        if(!matcher.matches())
            throw new InvalidCepException("invalid cep", value);

        return true;
    }
}
