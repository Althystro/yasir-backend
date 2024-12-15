package com.example.yasir.payments.controller;

import com.example.yasir.payments.bo.PaymentResponse;
import com.example.yasir.payments.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/makePayment/{id}")
    public ResponseEntity<PaymentResponse> makePayment(@PathVariable Long id) {
        PaymentResponse response = paymentService.makePayment(id);
        return ResponseEntity.ok(response);
    }
}
