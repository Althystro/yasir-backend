package com.example.yasir.payments.service;

import com.example.yasir.Auth.entity.User;
import com.example.yasir.Auth.repository.UserRepository;
import com.example.yasir.Vehicles.entity.VehicleEntity;
import com.example.yasir.Vehicles.repository.VehicleRepository;
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
import java.util.stream.StreamSupport;

@Service
public class PaymentPlanServiceImpl implements PaymentPlanService{

    private final FinancerRepository financerRepository;

    private final PaymentPlanRepository paymentPlanRepository;

    private final VehicleRepository vehicleRepository;

    private final UserRepository customerRepository;

    public PaymentPlanServiceImpl(FinancerRepository financerRepository, PaymentPlanRepository paymentPlanRepository, VehicleRepository vehicleRepository, UserRepository customerRepository) {
        this.financerRepository = financerRepository;
        this.paymentPlanRepository = paymentPlanRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public PaymentPlanResponse createPaymentPlan(Long customerId, Long vehicleId, Long financerId, int lengthMonths, double totalAmount) {
        User customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId));

        FinancerEntity financer = financerRepository.findById(financerId)
                .orElseThrow(() -> new ResourceNotFoundException("Financer not found with ID: " + financerId));

        double installmentAmount = totalAmount/lengthMonths;

        PaymentPlanEntity paymentPlan = new PaymentPlanEntity();
        paymentPlan.setCustomer(customer);
        paymentPlan.setVehicle(vehicle);
        paymentPlan.setFinancerId(financer);
        paymentPlan.setLengthMonths(lengthMonths);
        paymentPlan.setAmount(totalAmount);
        paymentPlan.setInstallmentAmount(installmentAmount);
        paymentPlan.setStatus(PaymentPlanStatus.PENDING);

        PaymentPlanEntity savedPlan = paymentPlanRepository.save(paymentPlan);

        return mapToResponse(savedPlan);
    }

    @Override
    public List<PaymentPlanResponse> getAllPaymentPlans() {
        List<PaymentPlanEntity> PaymentPlans = StreamSupport.stream(paymentPlanRepository.findAll().spliterator(),false).toList();
        return PaymentPlans.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public List<PaymentPlanResponse> getPaymentPlansByFinancerId(Long financerId) {
        FinancerEntity financer = financerRepository.findById(financerId).get();
        List<PaymentPlanEntity> paymentPlans = paymentPlanRepository.findByFinancerId(financer);
        return paymentPlans.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentPlanResponse> getPaymentPlansByUserId(Long userId) {
        User user = customerRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + userId));

        List<PaymentPlanEntity> paymentPlans = paymentPlanRepository.findByCustomer(user);

        return paymentPlans.stream().map(this::mapToResponse).collect(Collectors.toList());
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
