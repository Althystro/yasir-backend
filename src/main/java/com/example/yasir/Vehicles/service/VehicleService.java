package com.example.yasir.Vehicles.service;

import com.example.yasir.Vehicles.entity.VehicleEntity;

import java.util.ArrayList;

public interface VehicleService {

    public ArrayList<VehicleEntity> getAllVehicles();

    public VehicleEntity getSingleVehicle(Long id);
}
