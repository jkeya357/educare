package com.pm.userservice.exceptions;

public class InvalidTokenException extends GlobalException{

    public InvalidTokenException(String message) {
        super(message);
    }
}
