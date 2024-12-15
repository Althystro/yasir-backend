package com.example.yasir.payments.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "paymentPlanId", nullable = false)
    private PaymentPlanEntity paymentPlan;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentPlanEntity getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlanEntity paymentPlan) {
        this.paymentPlan = paymentPlan;
    }
}
