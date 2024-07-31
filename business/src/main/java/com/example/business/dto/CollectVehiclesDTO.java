package com.example.business.dto;

import java.sql.Date;

public interface CollectVehiclesDTO {
    Long getId();
    String getMake();
    String getMode();
    String getType();
    String getYear();
    Long getId_User();
    Float getAssociated_Cost();
    String getDescription();
    Date getService_Date();
}
