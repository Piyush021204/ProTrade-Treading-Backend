package com.piyush.service;

import com.piyush.domain.PaymentMethod;
import com.piyush.model.PaymentOrder;
import com.piyush.model.User;
import com.piyush.response.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentOrder createOrder(User user , Long amount, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean ProccedPaymentOrder(PaymentOrder paymentOrder,String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPaymentLing(User user, Long amount,Long orderId) throws RazorpayException;

    PaymentResponse createStripePaymentLing(User user, Long amount,Long orderId) throws StripeException;


}
