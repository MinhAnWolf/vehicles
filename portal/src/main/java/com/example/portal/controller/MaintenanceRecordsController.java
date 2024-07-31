package com.example.portal.controller;

import com.example.business.dto.AuthDTO;
import com.example.business.dto.MaintenanceRecordDTO;
import com.example.business.dto.VehicleDTO;
import com.example.business.service.CRUD;
import com.example.business.service.serviceImpl.MaintenanceRecordsServiceImpl;
import com.example.common.constant.ApiConstant;
import com.example.common.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API_MAINTENANCE_RECORDS)
public class MaintenanceRecordsController {
    @Autowired
    CRUD<MaintenanceRecordDTO, BaseResponse> maintenanceRecordsService;

    @PostMapping(ApiConstant.API_ADD)
    public ResponseEntity<?> add(@RequestBody MaintenanceRecordDTO maintenanceRecordDto, @RequestHeader("id") String userRq) {
        maintenanceRecordDto.setUserIdReq(Long.parseLong(userRq));
        return ResponseEntity.ok(maintenanceRecordsService.create(maintenanceRecordDto));
    }

//    @GetMapping(ApiConstant.API_VIEW)
//    public ResponseEntity<?> view(@RequestBody AuthDTO authDto) {
//        return ResponseEntity.ok();
//    }
//
//    @PatchMapping(ApiConstant.API_UPDATE)
//    public ResponseEntity<?> update(@RequestBody AuthDTO authDto) {
//        return ResponseEntity.ok();
//    }
//    @DeleteMapping(ApiConstant.API_DELETE)
//    public ResponseEntity<?> update(@RequestBody AuthDTO authDto) {
//        return ResponseEntity.ok();
//    }
}
