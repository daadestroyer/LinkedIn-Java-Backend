package com.linkedin.linkedin_user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
}
