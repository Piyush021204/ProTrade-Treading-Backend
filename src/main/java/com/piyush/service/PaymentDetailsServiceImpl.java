package com.piyush.service;

import com.piyush.model.PaymentDetails;
import com.piyush.model.User;
import com.piyush.repository.PaymentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService{

@Autowired
PaymentDetailsRepository paymentDetailsRepository;
    @Override
    public PaymentDetails addPaymentDetails(String accountNumber, String accountHolderName, String ifsc, String bankName, User user) {
       PaymentDetails paymentDetails = new PaymentDetails();

       paymentDetails.setAccountNumber(accountNumber);
       paymentDetails.setAccountHolderName(accountHolderName);
       paymentDetails.setIfsc(ifsc);
       paymentDetails.setBankName(bankName);
       paymentDetails.setUser(user);


        return paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public PaymentDetails getUserPaymentDetails(User user) {
        return paymentDetailsRepository.findByUserId(user.getId());
    }
}
