package com.supplynext.company_api.exceptions;

public class InvalidLoginCredentialsException extends RuntimeException {
    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}
