package com.sea.challenge.register.validators.impl;

import com.sea.challenge.register.models.PhoneType;
import com.sea.challenge.register.models.dtos.PhoneRequestDTO;
import com.sea.challenge.register.validators.PhoneNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, PhoneRequestDTO> {

    @Override
    public void initialize(PhoneNumber ph) {
    }

    @Override
    public boolean isValid(PhoneRequestDTO value, ConstraintValidatorContext arg1) {
        PhoneType phoneTypeValue = value.getPhoneType();
        String phoneValue = value.getPhone();

        switch (phoneTypeValue) {
            case CELPHONE:
                return phoneValue.matches("\\(\\d{2}\\) \\d{5}-\\d{4}");
            case RESIDENCIAL:
            case COMMERCIAL:
                return phoneValue.matches("\\(\\d{2}\\) \\d{4}-\\d{4}");
            default:
                return false;
        }
    }
}
