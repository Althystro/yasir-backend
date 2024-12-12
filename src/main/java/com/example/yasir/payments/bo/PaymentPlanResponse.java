package com.example.yasir.payments.bo;

public class PaymentPlanResponse {
    private Long paymentPlanId;
    private String customerName;
    private String vehicleModel;
    private String financerName;
    private int lengthMonths;
    private double totalAmount;
    private double installmentAmount;

    public Long getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(Long paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getFinancerName() {
        return financerName;
    }

    public void setFinancerName(String financerName) {
        this.financerName = financerName;
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

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }
}
