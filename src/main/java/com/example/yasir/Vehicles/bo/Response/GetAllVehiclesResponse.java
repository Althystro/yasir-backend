package com.example.yasir.Vehicles.bo.Response;

import com.example.yasir.Vehicles.entity.VehicleEntity;

import java.util.ArrayList;

public class GetAllVehiclesResponse {

    private ArrayList<VehicleEntity> vehicleEntities;

    private String error;

    public ArrayList<VehicleEntity> getVehicles() {
        return vehicleEntities;
    }

    public void setVehicles(ArrayList<VehicleEntity> vehicleEntities) {
        this.vehicleEntities = vehicleEntities;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
