package org.example.controller;

import org.example.dao.VehicleDao;
import org.example.model.Vehicle;

import java.util.List;

public class VehiclesController {

    private VehicleDao vehicleDao;

    public VehiclesController() {
        this.vehicleDao = new VehicleDao();
    }

    public List<Vehicle> getVehicles(Double minPrice, Double maxPrice, String make, String model, Integer minYear, Integer maxYear, String color, Integer minMiles, Integer maxMiles, String type) {
        return vehicleDao.searchVehicles(minPrice, maxPrice, make, model, minYear, maxYear, color, minMiles, maxMiles, type);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleDao.updateVehicle(vehicle);
    }

    public void deleteVehicle(int vin) {
        vehicleDao.deleteVehicle(vin);
    }

    public Vehicle getVehicleByVIN(int vin) {
        return vehicleDao.findVehicleByVIN(vin);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }
}
