package com.example.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResDTO {
    private Long id;
    private String username;
    private String token;
}
