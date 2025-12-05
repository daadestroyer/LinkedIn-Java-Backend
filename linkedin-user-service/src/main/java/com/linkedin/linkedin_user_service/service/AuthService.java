package com.linkedin.linkedin_user_service.service;

import com.linkedin.linkedin_user_service.dto.LoginRequestDto;
import com.linkedin.linkedin_user_service.dto.SignUpRequestDto;
import com.linkedin.linkedin_user_service.dto.UserDto;
import com.linkedin.linkedin_user_service.entity.User;
import com.linkedin.linkedin_user_service.exceptions.customexceptions.ResourceNotFoundException;
import com.linkedin.linkedin_user_service.exceptions.customexceptions.UnAuthorizedException;
import com.linkedin.linkedin_user_service.repository.UserRepository;
import com.linkedin.linkedin_user_service.utils.JWTService;
import com.linkedin.linkedin_user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) throws BadRequestException {
        boolean userExists = userRepository.existsByEmail(signUpRequestDto.getEmail());
        if(userExists){
            throw new BadRequestException("User already exists, can not signup again...");
        }
        User user = modelMapper.map(signUpRequestDto, User.class);

        user.setPassword(PasswordUtil.hashPassword(signUpRequestDto.getPassword()));

        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }


    public String login(LoginRequestDto loginRequestDto) {
        // checking if user exists by email
        User user = userRepository
                .findByEmail(loginRequestDto.getEmail()).orElseThrow(() ->
                        new ResourceNotFoundException("User not found with email " + loginRequestDto.getEmail()));

        // check if password provided by user and password store in db is matching or not
        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), user.getPassword());

        // if not matches throw exception
        if (!isPasswordMatch) {
            throw new UnAuthorizedException("Incorrect Exception");
        }
        // if matches give user jwt access token
        return jwtService.generateAccessToken(user);
    }
}
