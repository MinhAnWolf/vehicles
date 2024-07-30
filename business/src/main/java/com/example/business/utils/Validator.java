package com.example.business.utils;


import com.example.common.config.ExceptionHandler.*;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class Validator {
    @SneakyThrows
    public static void checkInputParameter(Object dto) {
        for (Field field : dto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(dto);
                if (value == null) {
                    throw new BadRequestException("Input invalid ");
                }
        }
    }
}
