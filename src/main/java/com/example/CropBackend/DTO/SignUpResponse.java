package com.example.CropBackend.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponse {
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
