package com.sea.challenge.register.exceptions.viacep;

import lombok.Getter;

@Getter
public class InvalidCepException extends RuntimeException {
    private String value;

    public InvalidCepException(String message, String value) {
        super(message);
        this.value = value;
    }
}
