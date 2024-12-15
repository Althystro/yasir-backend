package com.example.yasir.payments.bo;

public class CreatePaymentPlanRequest {
    private Long customerId;
    private Long vehicleId;
    private Long financerId;
    private int lengthMonths;
    private double totalAmount;

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getFinancerId() {
        return financerId;
    }

    public void setFinancerId(Long financerId) {
        this.financerId = financerId;
    }

    public int getLengthMonths() {
        return lengthMonths;
    }

    public void setLengthMonths(int lengthMonths) {
        this.lengthMonths = lengthMonths;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
