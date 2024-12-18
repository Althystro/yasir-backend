package com.example.yasir.Vehicles.bo.Response;

import com.example.yasir.Vehicles.entity.VehicleEntity;

import java.util.ArrayList;
import java.util.List;

public class GetAllVehiclesResponse {

    private List<VehicleEntity> vehicleEntities;

    private String error;

    public List<VehicleEntity> getVehicles() {
        return vehicleEntities;
    }

    public void setVehicles(List<VehicleEntity> vehicleEntities) {
        this.vehicleEntities = vehicleEntities;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
