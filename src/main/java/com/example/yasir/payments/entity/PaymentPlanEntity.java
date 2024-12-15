package com.example.yasir.payments.entity;

import com.example.yasir.Auth.entity.User;
import com.example.yasir.Vehicles.entity.VehicleEntity;
import com.example.yasir.payments.enums.PaymentPlanStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class PaymentPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentPlanStatus status = PaymentPlanStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User customer;

    @OneToOne
    @JoinColumn(name = "vehicleId")
    private VehicleEntity vehicle;
    private int lengthMonths;
    private double amount;
    private double installmentAmount;

    @ManyToOne
    @JoinColumn(name = "financerId")
    private FinancerEntity financerId;

    @OneToMany
    private List<PaymentEntity> payments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public int getLengthMonths() {
        return lengthMonths;
    }

    public void setLengthMonths(int lengthMonths) {
        this.lengthMonths = lengthMonths;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public FinancerEntity getFinancerId() {
        return financerId;
    }

    public void setFinancerId(FinancerEntity financerId) {
        this.financerId = financerId;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }

    public PaymentPlanStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentPlanStatus status) {
        this.status = status;
    }
}
