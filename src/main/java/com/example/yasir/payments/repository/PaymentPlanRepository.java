package com.example.yasir.payments.repository;

import com.example.yasir.Auth.entity.User;
import com.example.yasir.payments.entity.FinancerEntity;
import com.example.yasir.payments.entity.PaymentPlanEntity;
import com.example.yasir.payments.enums.PaymentPlanStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentPlanRepository extends CrudRepository<PaymentPlanEntity, Long> {
    //@Query("SELECT p FROM PaymentPlan p WHERE p.financer.id = :financerId")
    List<PaymentPlanEntity> findByFinancerId(FinancerEntity financerId);
    List<PaymentPlanEntity> findByFinancerIdAndStatus(FinancerEntity financer, PaymentPlanStatus status);
    List<PaymentPlanEntity> findByCustomer(User user);

}
