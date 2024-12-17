package com.example.yasir.payments.controller;

import com.example.yasir.payments.bo.FinancerResponse;
import com.example.yasir.payments.bo.PaymentPlanResponse;
import com.example.yasir.payments.bo.UpdatePaymentPlanStatusRequest;
import com.example.yasir.payments.service.FinancerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/financers")
public class FinancerController {

    private final FinancerService financerService;


    public FinancerController(FinancerService financerService) {
        this.financerService = financerService;
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<PaymentPlanResponse> updatePaymentPlanStatus(@PathVariable Long id, @RequestBody UpdatePaymentPlanStatusRequest newStatus) {
        PaymentPlanResponse response = financerService.updatePaymentPlanStatus(id, newStatus.getStatus());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<FinancerResponse> getAllFinancers() {
        FinancerResponse response = new FinancerResponse();
        response.setFinancer(financerService.getAllFinancers());
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/getPaymentPlans/{id}")
//    public ResponseEntity<List<PaymentPlanResponse>> getPendingPaymentPlans(@PathVariable Long id) {
//        List<PaymentPlanResponse> responses = financerService.getPendingPaymentPlans(id);
//        return ResponseEntity.ok(responses);
//    }
}
