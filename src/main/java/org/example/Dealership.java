package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.FileManager.getVehicles;
import static org.example.FileManager.writeVehicleToFile;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = getVehicles();
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory)
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max)
                vehicles.add(vehicle);
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory)
            if (vehicle.getMake().equals(make) && vehicle.getModel().equals(model))
                vehicles.add(vehicle);
        return vehicles;
    }

    public List<Vehicle> getVehiclesByYear(int year) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory)
            if (vehicle.getYear() == year)
                vehicles.add(vehicle);
        return vehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory)
            if (vehicle.getColor().equals(color))
                vehicles.add(vehicle);
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory)
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max)
                vehicles.add(vehicle);
        return vehicles;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory)
            if (vehicle.getVehicleType().equals(type))
                vehicles.add(vehicle);
        return vehicles;
    }

    public String getName() {
        return name;
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addVehicle(String addNewVehicle) {
        //vin(int), year(int), make(String), model(String), VehicleType(String), Color(String), odometer(int), price(double)
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Add a Vehicle: ");
            System.out.println("Enter VIN: ");
            int inputVin = scanner.nextInt();
            System.out.println("Enter Year of Vehicle: ");
            int inputYear = scanner.nextInt();
            System.out.println("Enter Make of the Vehicle: ");
            String inputMake = scanner.nextLine();
            System.out.println("Enter Model of the Vehicle: ");
            String inputModel = scanner.nextLine();
            System.out.println("Enter the Vehicle Type: Example: Car, Truck, SUV, Van. ");
            String inputType = scanner.nextLine();
            System.out.println("Enter the Color of the Vehicle: ");
            String inputColor = scanner.nextLine();
            System.out.println("Enter the odometer of the vehicle: ");
            int inputOdometer = scanner.nextInt();
            System.out.println("Enter the Price of the vehicle: ");
            double inputPrice = scanner.nextDouble();
            Vehicle newVehicle = new Vehicle(inputVin, inputYear, inputMake, inputModel, inputType, inputColor, inputOdometer, inputPrice);

            writeVehicleToFile(newVehicle);

            System.out.println("Vehicle has been successfully added to inventory! ");
        } catch (Exception ex) {
            System.out.println("Sorry, there was an error when adding the vehicle. Please try again. ");
        }
    }

    public void removeVehicleByVIN(int vinToRemove) {
        List<Vehicle> vehicles = getVehicles();
        boolean vehicleFound = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVin() == vinToRemove) {
                vehicles.remove(i);
                vehicleFound = true;
                break;
            }
        }

        if (vehicleFound) {
            writeVehicleToFile((Vehicle) vehicles);
            System.out.println("Vehicle with VIN " + vinToRemove + " has been successfully removed.");
        } else {
            System.out.println("No vehicle was found with VIN " + vinToRemove + ".");
        }
    }

}