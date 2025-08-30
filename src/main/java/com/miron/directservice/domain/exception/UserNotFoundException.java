package com.miron.directservice.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message, String username) {
        super(message);
        log.error("User with username {} not found", username);
    }
}
