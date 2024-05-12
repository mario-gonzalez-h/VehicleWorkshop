package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.FileManager.getVehicles;
import static org.example.FileManager.writeVehicleToFile;

public class
Dealership {
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

//    public void addVehicle(String addNewVehicle) {
//        //vin(int), year(int), make(String), model(String), VehicleType(String), Color(String), odometer(int), price(double)
//        Scanner scanner = new Scanner(System.in);
//        try {
//            System.out.println("Enter VIN: ");
//            int inputVin = scanner.nextInt();
//            System.out.println("Enter Year of Vehicle: ");
//            int inputYear = scanner.nextInt();
//            scanner.nextLine();
//            System.out.println("Enter Make of the Vehicle: ");
//            String inputMake = scanner.nextLine();
//            System.out.println("Enter Model of the Vehicle: ");
//            String inputModel = scanner.nextLine();
//            System.out.println("Enter the Vehicle Type: Example: Car, Truck, SUV, Van. ");
//            String inputType = scanner.nextLine();
//            System.out.println("Enter the Color of the Vehicle: ");
//            String inputColor = scanner.nextLine();
//            System.out.println("Enter the odometer of the vehicle: ");
//            int inputOdometer = scanner.nextInt();
//            System.out.println("Enter the Price of the vehicle: ");
//            double inputPrice = scanner.nextDouble();
//            Vehicle newVehicle = new Vehicle(inputVin, inputYear, inputMake, inputModel, inputType, inputColor, inputOdometer, inputPrice);
//
//            writeVehicleToFile(newVehicle);
//
//            System.out.println("Vehicle has been successfully added to inventory! ");
//        } catch (Exception ex) {
//            System.out.println("Sorry, there was an error when adding the vehicle. Please try again. ");
//        }
//    }

    public void addVehicle(String addNewVehicle) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter VIN (5 digits): ");
            int inputVin = scanner.nextInt();
            if (String.valueOf(inputVin).length() != 5) {
                throw new IllegalArgumentException("VIN must be 5 digits long.");
            }

            System.out.println("Enter Year of Vehicle (4 digits): ");
            int inputYear = scanner.nextInt();
            if (String.valueOf(inputYear).length() != 4) {
                throw new IllegalArgumentException("Year must be 4 digits long.");
            }

            scanner.nextLine();
            System.out.println("Enter Make of the Vehicle: ");
            String inputMake = scanner.nextLine();

            System.out.println("Enter Model of the Vehicle: ");
            String inputModel = scanner.nextLine();

            System.out.println("Enter the Vehicle Type: Example: Car, Truck, SUV, Van. ");
            String inputType = scanner.nextLine();

            System.out.println("Enter the Color of the Vehicle: ");
            String inputColor = scanner.nextLine();

            System.out.println("Enter the odometer of the vehicle (6 digits): ");
            String inputOdometerStr = scanner.nextLine();
            int inputOdometer = Integer.parseInt(inputOdometerStr);
            if (inputOdometerStr.length() != 6) {
                throw new IllegalArgumentException("Odometer reading must be 6 digits long.");
            }

            System.out.println("Enter the Price of the vehicle: ");
            double inputPrice = scanner.nextDouble();

            Vehicle newVehicle = new Vehicle(inputVin, inputYear, inputMake, inputModel, inputType, inputColor, inputOdometer, inputPrice);

            writeVehicleToFile(newVehicle);

            System.out.println("Vehicle has been successfully added to inventory! ");
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input for odometer. Please enter a valid number.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Input error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Sorry, there was an error when adding the vehicle. Please try again. ");
        } finally {
            scanner.nextLine();
        }
    }



    public void removeVehicleByVIN(int vinToRemove) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vinToRemove) {
                inventory.remove(i);
                FileManager.saveDealership(this);
                break;
            }
        }
        System.out.println("Could not find vehicle.");
    }
}