package com.piyush.service;

import com.piyush.domain.VerificationType;
import com.piyush.model.ForgotPasswordToken;
import com.piyush.model.User;

public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user , String id , String otp , VerificationType verificationType , String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId);

    void deleteToken(ForgotPasswordToken token);


}
