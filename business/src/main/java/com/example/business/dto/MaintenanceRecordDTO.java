package com.example.business.dto;

import com.example.business.entity.Vehicles;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecordDTO {
    private Long id;
    private Date serviceDate;
    private String description;
    private float associatedCost;
    private Vehicles vehicles;
    private Long userIdReq;
}
