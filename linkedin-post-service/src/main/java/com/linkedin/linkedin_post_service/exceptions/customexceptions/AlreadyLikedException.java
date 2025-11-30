package com.linkedin.linkedin_post_service.exceptions.customexceptions;

public class AlreadyLikedException extends RuntimeException{
    public AlreadyLikedException(String message) {
        super(message);
    }
}
