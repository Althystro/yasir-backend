package com.example.yasir.Vehicles.repository;

import com.example.yasir.Vehicles.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface VehicleRepository extends CrudRepository<VehicleEntity,Long> {
    ArrayList<VehicleEntity> findByBrand(String brand);
    ArrayList<VehicleEntity> findByYear(String year);
    VehicleEntity findByModel(String model);
}
