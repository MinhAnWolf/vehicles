package com.example.portal.controller;

import com.example.business.dto.AuthDTO;
import com.example.business.dto.VehicleDTO;
import com.example.business.service.CRUD;
import com.example.business.service.serviceImpl.VehiclesServiceImpl;
import com.example.common.constant.ApiConstant;
import com.example.common.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API_VEHICLES)
public class VehiclesController {
    @Autowired
    private VehiclesServiceImpl vehiclesService;

    @PostMapping(ApiConstant.API_ADD)
    public ResponseEntity<?> add(@RequestBody VehicleDTO vehicleDto) {
        return ResponseEntity.ok(vehiclesService.create(vehicleDto));
    }

    @GetMapping(ApiConstant.API_VIEW)
    public ResponseEntity<?> view(@RequestHeader("id") String userRq) {
        return ResponseEntity.ok(vehiclesService.readVehicle(Long.parseLong(userRq)));
    }

    @PatchMapping(ApiConstant.API_UPDATE)
    public ResponseEntity<?> update(@RequestBody VehicleDTO vehicleDto, @RequestHeader("id") String userRq) {
        vehicleDto.setUserId(Long.valueOf(userRq));
        return ResponseEntity.ok(vehiclesService.update(vehicleDto));
    }
//    @DeleteMapping(ApiConstant.API_DELETE)
//    public ResponseEntity<?> update(@RequestBody AuthDTO authDto) {
//        return ResponseEntity.ok();
//    }
}
