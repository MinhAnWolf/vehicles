package com.example.business.service.serviceImpl;

import com.example.business.dto.MaintenanceRecordDTO;
import com.example.business.dto.VehicleDTO;
import com.example.business.entity.MaintenanceRecords;
import com.example.business.entity.Users;
import com.example.business.entity.Vehicles;
import com.example.business.repository.MaintenanceRecordsRepository;
import com.example.business.repository.UsersRepository;
import com.example.business.repository.VehiclesRepository;
import com.example.business.service.CRUD;
import com.example.business.utils.Utils;
import com.example.business.utils.Validator;
import com.example.common.config.ExceptionHandler.*;
import com.example.common.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class MaintenanceRecordsServiceImpl implements CRUD<MaintenanceRecordDTO, BaseResponse> {
    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MaintenanceRecordsRepository maintenanceRecordsRepository;

    @Override
    public BaseResponse create(MaintenanceRecordDTO request) {
        request.setServiceDate(Utils.dateTimeNow());
        Validator.checkInputParameter(request);
        // check is owner or admin then action service
        Optional<Vehicles> vehicles = vehiclesRepository.findById(request.getVehicles().getId());
        Optional<Users> users = usersRepository.findById(request.getUserIdReq());
        BaseResponse baseResponse = new BaseResponse();
        if (vehicles.isPresent() && users.isPresent()) {
            Vehicles vehicle = vehicles.get();
            Users user = users.get();
            if (!request.getUserIdReq().equals(vehicle.getUsers().getId()) || !user.getRole().equals("ADMIN")) {
                throw new BadRequestException("You do not have to be the owner to perform this action.");
            }
            if (Objects.isNull(request.getId())) {
                baseResponse.setMessage("Created maintenance successfully");
            } else {
                baseResponse.setMessage("Update maintenance successfully");
            }
            baseResponse.setData(null);
            baseResponse.setErrCode(0);
            maintenanceRecordsRepository.save(convertEntity(request, vehicle));
        } else {
            baseResponse.setErrCode(1);
            baseResponse.setData(null);
            baseResponse.setMessage("Created maintenance fail");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse readOne(MaintenanceRecordDTO request) {
        return null;
    }

    @Override
    public BaseResponse update(MaintenanceRecordDTO request) {
        return null;
    }

    @Override
    public BaseResponse delete(MaintenanceRecordDTO request) {
        return null;
    }

    private MaintenanceRecords convertEntity(MaintenanceRecordDTO dto, Vehicles vehicles) {
        MaintenanceRecords entity = new MaintenanceRecords();
        if(dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setServiceDate(dto.getServiceDate());
        entity.setDescription(dto.getDescription());
        entity.setVehicles(vehicles);
        entity.setAssociatedCost(dto.getAssociatedCost());
        return entity;
    }
}
