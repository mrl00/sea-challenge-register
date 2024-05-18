package com.sea.challenge.register.validators.impl;

import com.sea.challenge.register.exceptions.InvalidPhoneNumberException;
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
            case CELLPHONE -> {
                if (!phoneValue.matches("\\(\\d{2}\\) \\d{5}-\\d{4}"))
                    throw new InvalidPhoneNumberException("cellphone number is invalid", phoneValue);
                yield true;
            }
            case RESIDENTIAL -> {
                if (!phoneValue.matches("\\(\\d{2}\\) \\d{4}-\\d{4}"))
                    throw new InvalidPhoneNumberException("residential number is invalid", phoneValue);
                yield true;
            }
            case COMMERCIAL -> {
                if (!phoneValue.matches("\\(\\d{2}\\) \\d{4}-\\d{4}"))
                    throw new InvalidPhoneNumberException("commercial number is invalid", phoneValue);
                yield true;
            }
            default -> false;
        };
    }
}
