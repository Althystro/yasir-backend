package com.example.yasir.Vehicles.service;

import com.example.yasir.Vehicles.entity.DealershipEntity;
import com.example.yasir.Vehicles.entity.VehicleEntity;

import java.util.ArrayList;
import java.util.List;

public interface VehicleService {

    public ArrayList<VehicleEntity> getAllVehicles();

    public VehicleEntity getSingleVehicle(Long id);

    List<DealershipEntity> getAllDealerships();

    List<VehicleEntity> getVehiclesByDealershipId(Long dealershipId);
}
