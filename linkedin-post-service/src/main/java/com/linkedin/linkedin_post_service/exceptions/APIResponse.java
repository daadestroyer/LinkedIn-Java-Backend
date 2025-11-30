package com.linkedin.linkedin_post_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class APIResponse {
    String message;
    HttpStatus code;


}
