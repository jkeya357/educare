package com.pm.userservice.exceptions;

public class UserNotFoundException extends GlobalException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
