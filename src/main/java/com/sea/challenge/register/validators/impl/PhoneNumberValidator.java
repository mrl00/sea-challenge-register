package com.sea.challenge.register.validators.impl;

import com.sea.challenge.register.models.dtos.PhoneDTO;
import com.sea.challenge.register.models.enums.PhoneType;
import com.sea.challenge.register.validators.PhoneNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, PhoneDTO> {

    @Override
    public boolean isValid(PhoneDTO value, ConstraintValidatorContext arg1) {
        PhoneType phoneTypeValue = value.getPhoneType();
        String phoneValue = value.getPhone();

        return switch (phoneTypeValue) {
            case CELLPHONE -> phoneValue.matches("\\(\\d{2}\\) \\d{5}-\\d{4}");
            case RESIDENTIAL, COMMERCIAL -> phoneValue.matches("\\(\\d{2}\\) \\d{4}-\\d{4}");
            default -> false;
        };
    }
}
