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
                System.out.println("Vehicle removed from inventory.");
                return;
            }
        }
        System.out.println("Could not find vehicle.");
    }

    public Vehicle selectVehicleByVIN() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the VIN of the vehicle you want to select:");
            int vinToSelect = Integer.parseInt(scanner.nextLine());

            for (Vehicle vehicle : inventory) {
                if (vehicle.getVin() == vinToSelect) {
                    System.out.println("Selected Vehicle:");
                    System.out.println(vehicle);
                    return vehicle;
                }
            }
            System.out.println("Vehicle not found.");
            return null;
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid integer for the VIN.");
            return null;
        }
    }

    public void processBuyCar(String dateOfContract, String customerName, String customerEmail) {
        System.out.println("You have selected to buy a car.");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to finance the purchase? (yes/no)");
        String financeChoice = scanner.nextLine();

        boolean finance = financeChoice.equalsIgnoreCase("yes");

        if (finance) {
            System.out.println("You have chosen to finance the purchase.");
        } else {
            System.out.println("You have chosen not to finance the purchase.");
        }

        Vehicle selectedVehicle = selectVehicleByVIN();
        if (selectedVehicle != null) {
    
            SalesContract contract = new SalesContract(dateOfContract, customerName, customerEmail, true, 0, 0, finance);
            contract.calculateTotalPrice();
            contract.calculateMonthlyPayment();
            ContractFileManager contractFileManager = new ContractFileManager();
            contractFileManager.saveContract(contract, selectedVehicle, this); 

            removeVehicleByVIN(selectedVehicle.getVin());
        }
    }

    public void processLeaseCar(String dateOfContract, String customerName, String customerEmail) {
        System.out.println("You have selected to lease a car.");
        Vehicle selectedVehicle = selectVehicleByVIN();
        if (selectedVehicle != null) {

            LeaseContract contract = new LeaseContract(dateOfContract, customerName, customerEmail, false, 0, 0);
            contract.calculateTotalPrice();
            contract.calculateMonthlyPayment();
            ContractFileManager contractFileManager = new ContractFileManager();
            contractFileManager.saveContract(contract, selectedVehicle, this); 


            if (!Vehicle.vehicleIsOlderThan3Years(selectedVehicle)) {
                removeVehicleByVIN(selectedVehicle.getVin());
            }
        }
    }
}
