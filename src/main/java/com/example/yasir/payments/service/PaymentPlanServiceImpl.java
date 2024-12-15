//package com.example.yasir.payments.service;
//
//import com.example.yasir.payments.bo.PaymentPlanResponse;
//import com.example.yasir.payments.entity.PaymentPlanEntity;
//import com.example.yasir.payments.repository.PaymentPlanRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class PaymentPlanServiceImpl implements PaymentPlanService{
//
//    private PaymentPlanRepository paymentPlanRepository;
//
//    public PaymentPlanResponse createPaymentPlan(Long customerId, Long vehicleId, Long financerId, int lengthMonths, double totalAmount) {
//        return null;
//    }
//
//    public List<PaymentPlanResponse> getAllPaymentPlans() {
//        List<PaymentPlanEntity> PaymentPlans = paymentPlanRepository.findAll();
//        return PaymentPlans.stream().map(this::mapToResponse).collect(Collectors.toList());
//    }
//
//    public List<PaymentPlanResponse> getPaymentPlansByFinancerId(Long financerId) {
//        List<PaymentPlanEntity> paymentPlans = paymentPlanRepository.findByFinancerId(financerId);
//        return paymentPlans.stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
//    }
//
//
//    private PaymentPlanResponse mapToResponse(PaymentPlanEntity paymentPlan) {
//        PaymentPlanResponse response = new PaymentPlanResponse();
//        response.setPaymentPlanId(paymentPlan.getId());
//        //response.setCustomerName(paymentPlan);
//        //response.setVehicleModel(paymentPlan.getVehicle().getModel());
//        //response.setFinancerName(paymentPlan.getFinancer().getName());
//        response.setLengthMonths(paymentPlan.getLengthMonths());
//        response.setTotalAmount(paymentPlan.getAmount());
//        response.setInstallmentAmount(paymentPlan.getInstallmentAmount());
//        return response;
//    }
//
//}
