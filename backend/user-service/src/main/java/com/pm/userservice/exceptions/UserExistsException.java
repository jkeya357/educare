package com.pm.userservice.exceptions;

public class UserExistsException extends GlobalException{

    public UserExistsException(String message) {
        super(message);
    }
}
