//package com.example.yasir.payments.entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class PaymentPlanEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Long customerId;
//    private Long vehicleId;
//    private int lengthMonths;
//    private double amount;
//    private double installmentAmount;
//
//    private FinancerEntity to;
//
//    @OneToMany
//    private List<PaymentEntity> payments;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }
//
//    public Long getVehicleId() {
//        return vehicleId;
//    }
//
//    public void setVehicleId(Long vehicleId) {
//        this.vehicleId = vehicleId;
//    }
//
//    public int getLengthMonths() {
//        return lengthMonths;
//    }
//
//    public void setLengthMonths(int lengthMonths) {
//        this.lengthMonths = lengthMonths;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public double getInstallmentAmount() {
//        return installmentAmount;
//    }
//
//    public void setInstallmentAmount(double installmentAmount) {
//        this.installmentAmount = installmentAmount;
//    }
//
//    public FinancerEntity getTo() {
//        return to;
//    }
//
//    public void setTo(FinancerEntity to) {
//        this.to = to;
//    }
//
//    public List<PaymentEntity> getPayments() {
//        return payments;
//    }
//
//    public void setPayments(List<PaymentEntity> payments) {
//        this.payments = payments;
//    }
//}
