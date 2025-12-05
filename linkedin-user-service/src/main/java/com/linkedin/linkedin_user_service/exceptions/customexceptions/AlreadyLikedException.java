package com.linkedin.linkedin_user_service.exceptions.customexceptions;

public class AlreadyLikedException extends RuntimeException{
    public AlreadyLikedException(String message) {
        super(message);
    }
}
