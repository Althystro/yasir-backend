package com.example.yasir.payments.service;

import com.example.yasir.payments.bo.PaymentResponse;
import com.example.yasir.payments.entity.PaymentEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

public interface PaymentService {
    PaymentResponse makePayment(Long paymentPlanId);
}
