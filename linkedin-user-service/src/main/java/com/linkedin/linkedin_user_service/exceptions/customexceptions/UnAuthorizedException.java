package com.linkedin.linkedin_user_service.exceptions.customexceptions;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message) {
        super(message);
    }
}
