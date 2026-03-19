package com.pm.enrollmentservice.exception;

public class StudentAllreadyRegisteredException extends EnrollmentException{

    public StudentAllreadyRegisteredException(String message) {
        super(message);
    }
}
