package com.linkedin.linkedin_user_service.exceptions.customexceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
