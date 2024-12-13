package com.example.yasir.payments.repository;

import com.example.yasir.payments.entity.FinancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancerRepository extends JpaRepository<FinancerEntity, Long> {
}
