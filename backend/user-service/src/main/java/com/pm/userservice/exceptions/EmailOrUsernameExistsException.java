package com.pm.userservice.exceptions;

public class EmailOrUsernameExistsException extends GlobalException{

    public EmailOrUsernameExistsException(String message) {
        super(message);
    }
}
