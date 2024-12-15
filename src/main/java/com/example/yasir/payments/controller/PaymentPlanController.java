package com.example.yasir.payments.controller;

import com.example.yasir.payments.bo.CreatePaymentPlanRequest;
import com.example.yasir.payments.bo.PaymentPlanResponse;
import com.example.yasir.payments.service.PaymentPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paymentPlans")
public class PaymentPlanController {

    private final PaymentPlanService paymentPlanService;

    public PaymentPlanController(PaymentPlanService paymentPlanService) {
        this.paymentPlanService = paymentPlanService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentPlanResponse> createPaymentPlan(@RequestBody CreatePaymentPlanRequest request) {
        PaymentPlanResponse response = paymentPlanService.createPaymentPlan(request.getCustomerId(), request.getVehicleId(), request.getFinancerId(), request.getLengthMonths(), request.getTotalAmount());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentPlanResponse>> getAllPaymentPlans() {
        List<PaymentPlanResponse> paymentPlans = paymentPlanService.getAllPaymentPlans();
        return ResponseEntity.ok(paymentPlans);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PaymentPlanResponse>> getPaymentPlansByUserId(@PathVariable Long id) {
        List<PaymentPlanResponse> paymentPlans = paymentPlanService.getPaymentPlansByUserId(id);
        return ResponseEntity.ok(paymentPlans);
    }
}
