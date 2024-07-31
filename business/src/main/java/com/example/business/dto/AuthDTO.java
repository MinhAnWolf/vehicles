package com.example.business.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthDTO {
    private String username;
    private String password;
    private String confirmPassword;
}
