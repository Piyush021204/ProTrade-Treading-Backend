package com.piyush.service;

import com.piyush.model.PaymentDetails;
import com.piyush.model.User;
import org.springframework.web.servlet.tags.Param;

public interface PaymentDetailsService {
    public PaymentDetails addPaymentDetails(String accountNumber , String accountHolder , String ifsc , String bankName, User user);

    public PaymentDetails getUserPaymentDetails(User user);




}
