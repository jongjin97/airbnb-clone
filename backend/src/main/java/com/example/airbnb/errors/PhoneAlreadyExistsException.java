package com.example.airbnb.errors;

import org.springframework.dao.DataIntegrityViolationException;

public class PhoneAlreadyExistsException extends DataIntegrityViolationException {

    public PhoneAlreadyExistsException(String message) {
        super(message);
    }

    public PhoneAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}