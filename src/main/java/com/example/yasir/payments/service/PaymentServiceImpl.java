package com.example.yasir.payments.service;

import com.example.yasir.payments.bo.PaymentResponse;
import com.example.yasir.payments.entity.PaymentEntity;
import com.example.yasir.payments.entity.PaymentPlanEntity;
import com.example.yasir.payments.enums.PaymentPlanStatus;
import com.example.yasir.payments.exceptions.ResourceNotFoundException;
import com.example.yasir.payments.repository.PaymentPlanRepository;
import com.example.yasir.payments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentPlanRepository paymentPlanRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentPlanRepository paymentPlanRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentPlanRepository = paymentPlanRepository;
    }

    @Override
    public PaymentResponse makePayment(Long paymentPlanId) {
        PaymentPlanEntity paymentPlan = paymentPlanRepository.findById(paymentPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Plan not found with ID: " + paymentPlanId));

        if (paymentPlan.getStatus() != PaymentPlanStatus.APPROVED) {
            throw new IllegalStateException("Cannot make a payment for a payment plan that is not approved.");
        }

        PaymentEntity payment = new PaymentEntity();

        payment.setDate(LocalDateTime.now());
        payment.setPaymentPlan(paymentPlan);

        PaymentEntity savedPayment = paymentRepository.save(payment);

        return mapToPaymentResponse(savedPayment);
    }

    private PaymentResponse mapToPaymentResponse(PaymentEntity payment) {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getId());
        response.setDate(payment.getDate());
        response.setPaymentPlanId(payment.getPaymentPlan().getId());
        response.setInstallmentAmount(payment.getPaymentPlan().getInstallmentAmount());
        return response;
    }

}
