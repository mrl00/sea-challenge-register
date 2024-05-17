package com.sea.challenge.register.exceptions;

import lombok.Getter;

@Getter
public class InvalidPhoneNumberException extends RuntimeException {
    private final String phone;

    public InvalidPhoneNumberException(String message, String phone) {
        super(message);
        this.phone = phone;
    }
}
