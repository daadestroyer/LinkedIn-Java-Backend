package com.linkedin.linkedin_post_service.exceptions;

import com.linkedin.linkedin_post_service.exceptions.customexceptions.AlreadyLikedException;
import com.linkedin.linkedin_post_service.exceptions.customexceptions.NotAbleToLikeException;
import com.linkedin.linkedin_post_service.exceptions.customexceptions.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFound(PostNotFoundException ex) {
        APIResponse apiResponse = new APIResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyLikedException.class)
    public ResponseEntity<APIResponse> handleAlreadyLikedException(AlreadyLikedException ex) {
        APIResponse apiResponse = new APIResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAbleToLikeException.class)
    public ResponseEntity<APIResponse> handleNotAbleToLikeException(NotAbleToLikeException ex) {
        APIResponse apiResponse = new APIResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
