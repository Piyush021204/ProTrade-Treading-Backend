package com.piyush.service;

import com.piyush.domain.VerificationType;
import com.piyush.model.User;
import com.piyush.model.VerificationCode;

public interface VerificationCodeService {
    VerificationCode sendVerificationCode(User user , VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);


    void deleteVerificationCodeById(VerificationCode verificationCode);
}
