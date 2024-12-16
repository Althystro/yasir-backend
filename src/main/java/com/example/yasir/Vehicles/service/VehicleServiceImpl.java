package com.example.yasir.Vehicles.service;

import com.example.yasir.Vehicles.entity.VehicleEntity;
import com.example.yasir.Vehicles.entity.DealershipEntity;
import com.example.yasir.Vehicles.repository.DealershipRepository;
import com.example.yasir.Vehicles.repository.VehicleRepository;
import com.example.yasir.Vehicles.Util.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DealershipRepository dealershipRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, DealershipRepository dealershipRepository) {
        this.dealershipRepository = dealershipRepository;
        this.vehicleRepository = vehicleRepository;
        populateDealershipsFromFile("src/main/java/com/example/yasir/Vehicles/Util/dealerShipData.txt");
        populateVehiclesFromFile("src/main/java/com/example/yasir/Vehicles/Util/carsData.txt");
    }

    @Override
    public ArrayList<VehicleEntity> getAllVehicles() {
        return new ArrayList<>(
                StreamSupport
                        .stream(vehicleRepository.findAll().spliterator(), false)
                        .toList()
        );
    }

    @Override
    public VehicleEntity getSingleVehicle(Long id){
        return vehicleRepository.findById(id).get();
    }

    public void populateVehiclesFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
            }

            String[] vehicleEntries = fileContent.toString().split("\"");
            for (String entry : vehicleEntries) {
                VehicleEntity vehicleEntity = parseVehicle(entry);
                if (vehicleEntity != null) {
                    assert vehicleRepository != null;
                    //vehicleRepository.save(vehicleEntity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file: " + filePath, e);
        }
    }

    private VehicleEntity parseVehicle(String entry) {
        try {
            String[] fields = entry.split("\\|");

            String type = fields[0].split(":")[1].toUpperCase();
            String brand = fields[1].split(":")[1];
            String model = fields[2].split(":")[1];
            String year = fields[3].split(":")[1];
            String image = fields[4].split(";")[1];
            String image2 = fields[5].split(";")[1];
            Double price = Double.parseDouble(fields[6].split(":")[1]);

            VehicleEntity vehicleEntity = new VehicleEntity();
            vehicleEntity.setType(Type.valueOf(type));
            vehicleEntity.setBrand(brand);
            vehicleEntity.setModel(model);
            vehicleEntity.setYear(year);
            vehicleEntity.setImage(image);
            vehicleEntity.setImage2(image2);
            vehicleEntity.setPrice(price);
            vehicleRepository.save(vehicleEntity);

            if (brand.equals("Ford")){
                DealershipEntity dealership = dealershipRepository.findByName("Alghanim");
                dealership.addVehicle(vehicleEntity);
                dealershipRepository.save(dealership);
            }
            if (brand.equals("Chevrolet")){
                DealershipEntity dealership = dealershipRepository.findByName("Alghanim");
                dealership.addVehicle(vehicleEntity);
                dealershipRepository.save(dealership);
            }
            if (brand.equals("Toyota")){
                DealershipEntity dealership = dealershipRepository.findByName("AlSayer");
                dealership.addVehicle(vehicleEntity);
                dealershipRepository.save(dealership);
            }
            if (brand.equals("Porsche")){
                DealershipEntity dealership = dealershipRepository.findByName("Behbehani");
                dealership.addVehicle(vehicleEntity);
                dealershipRepository.save(dealership);
            }
            if (brand.equals("Mercedes-Benz")){
                DealershipEntity dealership = dealershipRepository.findByName("Al Mulla");
                dealership.addVehicle(vehicleEntity);
                dealershipRepository.save(dealership);
            }



            return vehicleEntity;
        } catch (Exception e) {
            System.err.println("Error parsing vehicle entry: " + entry);
            e.printStackTrace();
            return null;
        }
    }

    public void populateDealershipsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
            }

            String[] dealershipEntries = fileContent.toString().split(",");
            for (String entry : dealershipEntries) {
                DealershipEntity dealershipEntity = parseDealership(entry);
                if (dealershipEntity != null) {
                    assert dealershipRepository != null;
                    dealershipRepository.save(dealershipEntity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file: " + filePath, e);
        }
    }

    private DealershipEntity parseDealership(String entry) {
        try {
            String[] fields = entry.split("\\|");

            String name = fields[0].split(":")[1];
            String address = fields[1].split(":")[1];
            String email = fields[2].split(":")[1];
            String phoneNumber = fields[3].split(":")[1];

            DealershipEntity dealershipEntity = new DealershipEntity();
            dealershipEntity.setName(name);
            dealershipEntity.setAddress(address);
            dealershipEntity.setEmail(email);
            dealershipEntity.setPhoneNumber(phoneNumber);

            return dealershipEntity;
        } catch (Exception e) {
            System.err.println("Error parsing dealership entry: " + entry);
            e.printStackTrace();
            return null;
        }
    }
}
