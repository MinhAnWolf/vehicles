package com.example.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long id;

    private String make;

    private String model;

    private String year;

    private String type;

    private Long userId;
}
