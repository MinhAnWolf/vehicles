package com.example.business.service.serviceImpl;

import com.example.business.dto.VehicleDTO;
import com.example.business.entity.Users;
import com.example.business.entity.Vehicles;
import com.example.business.repository.UsersRepository;
import com.example.business.repository.VehiclesRepository;
import com.example.business.service.CRUD;
import com.example.business.utils.Validator;
import com.example.common.Log;
import com.example.common.config.ExceptionHandler.*;
import com.example.common.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class VehiclesServiceImpl implements CRUD<VehicleDTO, BaseResponse> {
    private static final String SERVICE_NAME = "VehiclesServiceImpl";
    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public BaseResponse create(VehicleDTO request) {
        Log.startLog(SERVICE_NAME, "create");
        Log.inputLog(request);
        Validator.checkInputParameter(request);
        try {
            Vehicles vehicle = convertEntity(request);
            if (Objects.isNull(vehicle)) {
                Log.outputLog("User created exist");
                Log.endLog(SERVICE_NAME, "create");
                throw new BadRequestException("Vehicle created fail");
            }
            vehiclesRepository.save(vehicle);
            BaseResponse baseResponse =
                    new BaseResponse(0, null, "Vehicle created successfully");
            Log.outputLog(baseResponse);
            Log.endLog(SERVICE_NAME, "create");
            return baseResponse;
        } catch (Exception e) {
            Log.errorLog(e.getMessage());
            Log.endLog(SERVICE_NAME, "create");
            throw new SystemErrorException("System error");
        }
    }

    @Override
    public BaseResponse readOne(VehicleDTO request) {
        return null;
    }

    @Override
    public BaseResponse update(VehicleDTO request) {
        if(Objects.isNull(request.getId())) {
            Log.outputLog("Vehicle is not exist");
            throw new BadRequestException("Vehicle is not exist");
        }
        BaseResponse baseResponse = new BaseResponse();
        Optional<Users> usersOptional = usersRepository.findById(request.getUserId());
        Optional<Vehicles> vehiclesOptional = vehiclesRepository.findById(request.getId());
        if(usersOptional.isPresent() && vehiclesOptional.isPresent()) {
            Users user = usersOptional.get();
            Vehicles vehicle = vehiclesOptional.get();
            if (vehicle.getUsers().getId().equals(request.getUserId()) || user.getRole().equals("ADMIN")) {
                Validator.checkInputParameter(request);
                Vehicles setData = convertEntity(request);
                setData.setId(request.getId());
                vehiclesRepository.save(setData);
                baseResponse.setData(null);
                baseResponse.setErrCode(0);
                baseResponse.setMessage("Update vehicle successfully");
            }
        }
        return baseResponse;
    }

    @Override
    public BaseResponse delete(VehicleDTO request) {
        return null;
    }

    public BaseResponse readVehicle(Long userId) {
        // check is owner or admin then action service
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(vehiclesRepository.getCollectVehicles(userId));
        baseResponse.setErrCode(0);
        baseResponse.setMessage("Fetch data successfully");
        return baseResponse;
    }

    private Vehicles convertEntity(VehicleDTO dto) {
        Optional<Users> usersFetch = usersRepository.findById(dto.getUserId());
        if (usersFetch.isPresent()) {
            Vehicles entity = new Vehicles();
            entity.setMake(dto.getMake());
            entity.setModel(dto.getModel());
            entity.setYear(dto.getYear());
            entity.setType(dto.getType());
            entity.setUsers(usersFetch.get());
            return entity;
        }
        return null ;
    }
}
