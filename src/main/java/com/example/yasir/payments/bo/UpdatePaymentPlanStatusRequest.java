package com.example.yasir.payments.bo;

import com.example.yasir.payments.enums.PaymentPlanStatus;

public class UpdatePaymentPlanStatusRequest {
    private PaymentPlanStatus status;

    public PaymentPlanStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentPlanStatus status) {
        this.status = status;
    }
}
