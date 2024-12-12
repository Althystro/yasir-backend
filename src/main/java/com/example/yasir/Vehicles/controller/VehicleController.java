package com.example.yasir.Vehicles.controller;

import com.example.yasir.Vehicles.bo.Response.GetAllVehiclesResponse;
import com.example.yasir.Vehicles.entity.VehicleEntity;
import com.example.yasir.Vehicles.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequestMapping("/vehicle")
@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }



    @GetMapping("/all")
    public ResponseEntity<GetAllVehiclesResponse> getAllVehicles(){
        try {
            ArrayList<VehicleEntity> allVehicleEntities = vehicleService.getAllVehicles();
            GetAllVehiclesResponse response = new GetAllVehiclesResponse();
            response.setVehicles(allVehicleEntities);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            GetAllVehiclesResponse response = new GetAllVehiclesResponse();
            response.setError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }


}
