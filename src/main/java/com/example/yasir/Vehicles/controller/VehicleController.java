package com.example.yasir.Vehicles.controller;

import com.example.yasir.Vehicles.bo.Response.GetAllDealershipsResponse;
import com.example.yasir.Vehicles.bo.Response.GetAllVehiclesResponse;
import com.example.yasir.Vehicles.bo.Response.GetCarByIdResponse;
import com.example.yasir.Vehicles.entity.DealershipEntity;
import com.example.yasir.Vehicles.entity.VehicleEntity;
import com.example.yasir.Vehicles.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<GetCarByIdResponse> getSingleVehicle(@PathVariable Long id){
        GetCarByIdResponse response = new GetCarByIdResponse();
        try {
            VehicleEntity vehicle = vehicleService.getSingleVehicle(id);
            response.setVehicle(vehicle);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setError(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


    }

    @GetMapping("/dealership/{dealershipId}")
    public ResponseEntity<GetAllVehiclesResponse> getVehiclesByDealershipId(@PathVariable Long dealershipId){
        try {
            List<VehicleEntity> allVehicleEntities = vehicleService.getVehiclesByDealershipId(dealershipId);
            GetAllVehiclesResponse response = new GetAllVehiclesResponse();
            response.setVehicles(allVehicleEntities);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            GetAllVehiclesResponse response = new GetAllVehiclesResponse();
            response.setError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/dealership")
    public ResponseEntity<GetAllDealershipsResponse> getAllDealerships(){
        try {
            List<DealershipEntity> allDealershipEntities = vehicleService.getAllDealerships();
            GetAllDealershipsResponse response = new GetAllDealershipsResponse();
            response.setDealerships(allDealershipEntities);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            GetAllDealershipsResponse response = new GetAllDealershipsResponse();
            response.setError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }


}
