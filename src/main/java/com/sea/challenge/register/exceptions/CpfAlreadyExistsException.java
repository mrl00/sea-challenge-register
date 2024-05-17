package com.sea.challenge.register.exceptions;

import lombok.Getter;

@Getter
public class CpfAlreadyExistsException extends RuntimeException {

    private final String cpf;

    public CpfAlreadyExistsException(String message, String cpf) {
        super(message);
        this.cpf = cpf;
    }
}
