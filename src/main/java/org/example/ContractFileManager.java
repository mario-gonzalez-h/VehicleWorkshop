package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;

public class ContractFileManager {

    public void saveContract(Contract contract, Vehicle selectedVehicle, Dealership dealership) {
        if (contract instanceof LeaseContract) {
            saveLeaseContract((LeaseContract) contract, selectedVehicle);
        } else if (contract instanceof SalesContract) {
            saveSalesContract((SalesContract) contract, selectedVehicle);
        } else {
            throw new IllegalArgumentException("Unsupported contract type");
        }

        // After saving the contract, remove the vehicle from inventory if it's not older than 3 years
        if (!Vehicle.vehicleIsOlderThan3Years(selectedVehicle)) {
            dealership.removeVehicleByVIN(selectedVehicle.getVin());
        }
    }

    private void saveLeaseContract(LeaseContract leaseContract, Vehicle selectedVehicle) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/contracts.csv", true))) {
            if (selectedVehicle != null) {
                int currentYear = Year.now().getValue();
                int vehicleYear = selectedVehicle.getYear();
                int vehicleAge = currentYear - vehicleYear;

                System.out.println("Current year: " + currentYear);
                System.out.println("Vehicle year: " + vehicleYear);
                System.out.println("Vehicle age: " + vehicleAge);

                if (vehicleAge > 3) {
                    System.out.println("Vehicle is older than 3 years and cannot be leased.");
                    return; // Do not save the lease contract or remove the vehicle from inventory
                }

                writer.write("LEASE|"
                        + leaseContract.getDateOfContract() + "|"
                        + leaseContract.getCustomerName() + "|"
                        + leaseContract.getCustomerEmail() + "|"
                        + selectedVehicle.getVin() + "|"
                        + selectedVehicle.getYear() + "|"
                        + selectedVehicle.getMake() + "|"
                        + selectedVehicle.getModel() + "|"
                        + selectedVehicle.getVehicleType() + "|"
                        + selectedVehicle.getColor() + "|"
                        + selectedVehicle.getOdometer() + "|"
                        + String.format("%.2f", selectedVehicle.getPrice()) + "|"
                        + String.format("%.2f", leaseContract.getExpectedEndingValue()) + "|"
                        + String.format("%.2f", leaseContract.getLeaseFee()) + "|"
                        + String.format("%.2f", leaseContract.getMonthlyPayment()) + "\n"); // Ensure to include "\n" to separate each entry
            } else {
                System.out.println("No vehicle selected.");
            }
        } catch (IOException e) {
            System.err.println("Error writing lease contract to file: " + e.getMessage());
        }
    }

    private void saveSalesContract(SalesContract salesContract, Vehicle selectedVehicle) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/contracts.csv", true))) {
            if (selectedVehicle != null) {
                writer.write("SALE|"
                        + salesContract.getDateOfContract() + "|"
                        + salesContract.getCustomerName() + "|"
                        + salesContract.getCustomerEmail() + "|"
                        + selectedVehicle.getVin() + "|"
                        + selectedVehicle.getYear() + "|"
                        + selectedVehicle.getMake() + "|"
                        + selectedVehicle.getModel() + "|"
                        + selectedVehicle.getVehicleType() + "|"
                        + selectedVehicle.getColor() + "|"
                        + selectedVehicle.getOdometer() + "|"
                        + String.format("%.2f", Vehicle.getPrice()) + "|"
                        + String.format("%.2f", salesContract.getTotalPrice()) + "|"
                        + String.format("%.2f", salesContract.getSalesTaxAmount()) + "|"
                        + String.format("%.2f", salesContract.getRecordingFee()) + "|"
                        + String.format("%.2f", salesContract.getProcessingFee()) + "|"
                        + (salesContract.isFinance() ? "YES" : "NO") + "|"
                        + (salesContract.isFinance() ? String.format("%.2f", salesContract.getMonthlyPayment()) : "No finance") + "\n");
            } else {
                System.out.println("No vehicle selected.");
            }
        } catch (IOException e) {
            System.err.println("Error writing sales contract to file: " + e.getMessage());
        }
    }
}
