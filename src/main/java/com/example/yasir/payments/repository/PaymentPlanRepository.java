//package com.example.yasir.payments.repository;
//
//import com.example.yasir.payments.entity.PaymentPlanEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface PaymentPlanRepository extends JpaRepository<PaymentPlanEntity, Long> {
//    @Query("SELECT p FROM PaymentPlan p WHERE p.financer.id = :financerId")
//    List<PaymentPlanEntity> findByFinancerId(@Param("financerId") Long financerId);
//}
