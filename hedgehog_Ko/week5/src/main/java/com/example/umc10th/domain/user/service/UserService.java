package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String singleParameter(
            String singleParameter
    ) {
        return singleParameter;
    }

//    // Request Body
//    public UserResDTO.RequestBody requestBody(
//            UserReqDTO.RequestBody dto
//    ) {
//        return UserConverter.toRequestBody(dto.stringTest(), dto.longTest());
//    }
}
