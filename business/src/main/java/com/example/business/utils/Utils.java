package com.example.business.utils;

import java.sql.Date;

public class Utils {
    public static Date dateTimeNow() {
        java.util.Date utilDate = new java.util.Date();
        return new Date(utilDate.getTime());
    }
}
