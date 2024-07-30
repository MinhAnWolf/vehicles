package com.example.portal.controller;

import com.example.business.dto.AuthDTO;
import com.example.common.constant.ApiConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API_MAINTENANCE_RECORDS)
public class MaintenanceRecordsController {
    @PostMapping(ApiConstant.API_ADD)
    public ResponseEntity<?> add(@RequestBody AuthDTO authDto) {
        return ResponseEntity.ok();
    }

    @GetMapping(ApiConstant.API_VIEW)
    public ResponseEntity<?> view(@RequestBody AuthDTO authDto) {
        return ResponseEntity.ok();
    }

    @GetMapping(ApiConstant.API_UPDATE)
    public ResponseEntity<?> update(@RequestBody AuthDTO authDto) {
        return ResponseEntity.ok();
    }
}
