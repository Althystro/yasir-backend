package com.example.yasir.payments.bo;

import java.time.LocalDateTime;

public class PaymentResponse {
    private Long paymentId;
    private LocalDateTime date;
    private Long paymentPlanId;
    private double installmentAmount;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(Long paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }
}
