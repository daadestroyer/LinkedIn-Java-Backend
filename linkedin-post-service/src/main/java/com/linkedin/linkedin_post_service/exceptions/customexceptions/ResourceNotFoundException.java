package com.linkedin.linkedin_post_service.exceptions.customexceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
