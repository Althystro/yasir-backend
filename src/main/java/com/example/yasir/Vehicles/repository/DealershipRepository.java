package com.example.yasir.Vehicles.repository;

import com.example.yasir.Vehicles.entity.DealershipEntity;
import org.springframework.data.repository.CrudRepository;

public interface DealershipRepository extends CrudRepository<DealershipEntity,Long> {
    DealershipEntity findByName(String name);
}
