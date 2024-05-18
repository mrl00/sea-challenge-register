package com.sea.challenge.register.exceptions.security;

import lombok.Getter;

@Getter
public class UserNameAlreadyExistsException extends RuntimeException {
    private final String username;

    public UserNameAlreadyExistsException(String message, String username) {
        super(message);
        this.username = username;
    }
}
