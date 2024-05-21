package com.sea.challenge.register.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class EmailAlreadyExistsException extends RuntimeException {
    private final List<String> emails;

    public EmailAlreadyExistsException(String message, List<String> emails) {
        super(message);
        this.emails = emails;
    }
}
