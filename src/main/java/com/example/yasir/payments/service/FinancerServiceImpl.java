package com.example.yasir.payments.service;

import com.example.yasir.payments.bo.PaymentPlanResponse;
import com.example.yasir.payments.entity.FinancerEntity;
import com.example.yasir.payments.entity.PaymentPlanEntity;
import com.example.yasir.payments.enums.PaymentPlanStatus;
import com.example.yasir.payments.exceptions.ResourceNotFoundException;
import com.example.yasir.payments.repository.FinancerRepository;
import com.example.yasir.payments.repository.PaymentPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancerServiceImpl implements FinancerService {

    private final FinancerRepository financerRepository;
    private final PaymentPlanRepository paymentPlanRepository;

    public FinancerServiceImpl(FinancerRepository financerRepository, PaymentPlanRepository paymentPlanRepository) {
        this.financerRepository = financerRepository;
        this.paymentPlanRepository = paymentPlanRepository;
    }

    @Override
    public PaymentPlanResponse updatePaymentPlanStatus(Long paymentPlanId, PaymentPlanStatus newStatus) {
        PaymentPlanEntity paymentPlan = paymentPlanRepository.findById(paymentPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Plan not found with ID: " + paymentPlanId));

        if (paymentPlan.getStatus() != PaymentPlanStatus.PENDING) {
            throw new IllegalStateException("Only pending payment plans can be updated.");
        }

        if (newStatus != PaymentPlanStatus.APPROVED && newStatus != PaymentPlanStatus.REJECTED) {
            throw new IllegalArgumentException("Invalid status update. Only APPROVED or REJECTED statuses are allowed.");
        }

        paymentPlan.setStatus(newStatus);
        PaymentPlanEntity updatedPlan = paymentPlanRepository.save(paymentPlan);

        return mapToResponse(updatedPlan);
    }

    @Override
    public List<PaymentPlanResponse> getPendingPaymentPlans(Long financerId) {
        FinancerEntity financer = financerRepository.findById(financerId)
                .orElseThrow(() -> new ResourceNotFoundException("Financer not found with ID: " + financerId));

        List<PaymentPlanEntity> pendingPlans = paymentPlanRepository.findByFinancerIdAndStatus(financer, PaymentPlanStatus.PENDING);

        return pendingPlans.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentPlanResponse mapToResponse(PaymentPlanEntity paymentPlan) {
        PaymentPlanResponse response = new PaymentPlanResponse();
        response.setPaymentPlanId(paymentPlan.getId());
        response.setCustomerName(paymentPlan.getCustomer().getFirstName());
        response.setVehicleModel(paymentPlan.getVehicle().getModel());
        response.setFinancerName(paymentPlan.getFinancerId().getName());
        response.setLengthMonths(paymentPlan.getLengthMonths());
        response.setTotalAmount(paymentPlan.getAmount());
        response.setInstallmentAmount(paymentPlan.getInstallmentAmount());
        response.setStatus(paymentPlan.getStatus());
        return response;
    }
}
