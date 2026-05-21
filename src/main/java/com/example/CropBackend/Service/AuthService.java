package com.example.CropBackend.Service;

import com.example.CropBackend.DTO.LoginRequest;
import com.example.CropBackend.DTO.LoginResponse;
import com.example.CropBackend.DTO.SignUpRequest;
import com.example.CropBackend.DTO.SignUpResponse;
import com.example.CropBackend.Entity.User;
import com.example.CropBackend.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public SignUpResponse userSignUp(SignUpRequest signUpRequest) {
        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User newUser = User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        User savedUser = userRepo.save(newUser);

        return SignUpResponse.builder()
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    public LoginResponse userLogin(LoginRequest loginRequest) {
        User existingUser = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        boolean isMatch =
                passwordEncoder.matches(
                        loginRequest.getPassword(),
                        existingUser.getPassword()
                );

        if (!isMatch) {
            throw new RuntimeException(
                    "Invalid password"
            );
        }

        return LoginResponse.builder()
                .name(existingUser.getName())
                .email(existingUser.getEmail())
                .message("Login Successfully")
                .build();

    }
}
