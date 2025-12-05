package com.linkedin.linkedin_user_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class APIResponse {
    String message;
    HttpStatus code;


}
