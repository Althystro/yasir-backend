package com.example.yasir.Vehicles.repository;

import com.example.yasir.Vehicles.entity.DealershipEntity;
import com.example.yasir.Vehicles.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DealershipRepository extends CrudRepository<DealershipEntity,Long> {
    DealershipEntity findByName(String name);
}
