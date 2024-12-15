package com.example.yasir.Vehicles.bo.Response;

import com.example.yasir.Vehicles.entity.VehicleEntity;

public class GetCarByIdResponse {

    private VehicleEntity vehicle;

    private String error;

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
