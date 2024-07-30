package com.example.business.service;

import com.example.business.dto.AuthDTO;
import com.example.common.model.BaseResponse;

public interface AuthService {
    BaseResponse register(AuthDTO authDto);
    BaseResponse login(AuthDTO authDto);
}
