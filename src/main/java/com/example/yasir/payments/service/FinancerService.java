package com.example.yasir.payments.service;

import com.example.yasir.payments.bo.PaymentPlanResponse;
import com.example.yasir.payments.enums.PaymentPlanStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FinancerService {
    PaymentPlanResponse updatePaymentPlanStatus(Long paymentPlanId, PaymentPlanStatus newStatus);
    List<PaymentPlanResponse> getPendingPaymentPlans(Long financerId);
}
