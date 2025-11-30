package com.linkedin.linkedin_post_service.exceptions.customexceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
