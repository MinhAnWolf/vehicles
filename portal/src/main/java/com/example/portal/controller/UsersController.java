package com.example.portal.controller;

import com.example.business.dto.AuthDTO;
import com.example.business.service.AuthService;
import com.example.common.constant.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API_AUTH)
public class UsersController {
    @Autowired
    private AuthService authService;

    @PostMapping(ApiConstant.API_REGISTER)
    public ResponseEntity<?> register(@RequestBody AuthDTO authDto) {
        return ResponseEntity.ok(authService.register(authDto));
    }

    @PostMapping(ApiConstant.API_LOGIN)
    public ResponseEntity<?> login(@RequestBody AuthDTO authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }

    @GetMapping("/test")
    public ResponseEntity<?> logixn() {
        return ResponseEntity.ok("hello world");
    }

}
