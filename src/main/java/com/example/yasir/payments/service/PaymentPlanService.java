package com.example.yasir.payments.service;

import com.example.yasir.payments.bo.PaymentPlanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PaymentPlanService {
    PaymentPlanResponse createPaymentPlan(Long customerId, Long vehicleId, Long financerId, int lengthMonths, double totalAmount);
    List<PaymentPlanResponse> getAllPaymentPlans();
    List<PaymentPlanResponse> getPaymentPlansByFinancerId(Long financerId);
    List<PaymentPlanResponse> getPaymentPlansByUserId(Long userId);

}
