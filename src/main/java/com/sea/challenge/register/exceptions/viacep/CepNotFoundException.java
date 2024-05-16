package com.sea.challenge.register.exceptions.viacep;

import lombok.Getter;

@Getter
public class CepNotFoundException extends RuntimeException {
    private String value;

    public CepNotFoundException(String message, String value) {
        super(message);
        this.value = value;
    }
}
