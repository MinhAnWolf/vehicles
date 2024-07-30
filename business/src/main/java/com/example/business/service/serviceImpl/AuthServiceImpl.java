package com.example.business.service.serviceImpl;

import com.example.business.dto.AuthDTO;
import com.example.business.dto.UserResDTO;
import com.example.business.entity.Users;
import com.example.business.repository.UsersRepository;
import com.example.business.service.AuthService;
import com.example.business.utils.PasswordUtils;
import com.example.business.utils.TokenUtils;
import com.example.business.utils.Validator;
import com.example.common.Log;
import com.example.common.config.ExceptionHandler;
import com.example.common.config.ExceptionHandler.*;
import com.example.common.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String SERVICE_NAME = "AuthServiceImpl";
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public BaseResponse register(AuthDTO authDto) {
        try {
            Log.startLog("Register", SERVICE_NAME);
            Log.inputLog(authDto);
            Validator.checkInputParameter(authDto);
            // check user exist
            Integer exist = usersRepository.countUsersByUsername(authDto.getUsername());
            if (exist > 0) {
                throw new BadRequestException("Username is already taken");
            }
            // register user
            BaseResponse baseResponse = new BaseResponse();
            Users user = new Users();
            user.setUsername(authDto.getUsername());
            user.setPassword(PasswordUtils.encodePassword(authDto.getPassword()));
            user.setRole("USER");
            usersRepository.save(user);
            baseResponse.setErrCode(0);
            baseResponse.setMessage("Register success");
            baseResponse.setData(null);
            Log.outputLog(baseResponse);
            Log.endLog("Register", SERVICE_NAME);
            return baseResponse;
        } catch (Exception e) {
            String messageError = e.getMessage();
            Log.outputLog(messageError);
            Log.endLog("Register", SERVICE_NAME);
            throw new SystemErrorException(messageError);
        }
    }

    @Override
    public BaseResponse login(AuthDTO authDto) {
        Log.startLog("Login", SERVICE_NAME);
        Log.inputLog(authDto);
        if (authDto.getUsername() == null || authDto.getPassword() == null) {
            Log.outputLog("Login fail");
            Log.endLog("Login", SERVICE_NAME);
            throw new BadRequestException("Username or password is incorrect");
        }
        Users users = usersRepository.getUserByUsername(authDto.getUsername());
        if (PasswordUtils.matchesPassword(authDto.getPassword(), users.getPassword())) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setErrCode(0);
            baseResponse.setData(TokenUtils.SECRET_KEY);
            baseResponse.setMessage("Login success");
            Log.outputLog(baseResponse);
            Log.endLog("Login", SERVICE_NAME);
            return new BaseResponse(0, new UserResDTO(users.getId(), users.getUsername(), tokenUtils.generateToken(users)) , "Login success");
        }
        return new BaseResponse(0, null, "Login fail");
    }
}
