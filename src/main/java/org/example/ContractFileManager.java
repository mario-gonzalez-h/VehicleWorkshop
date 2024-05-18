package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;

public class ContractFileManager {
    public void saveContract(Contract contract, Vehicle selectedVehicle) {
        if (contract instanceof LeaseContract) {
            saveLeaseContract((LeaseContract) contract, selectedVehicle);
        } else if (contract instanceof SalesContract) {
            saveSalesContract((SalesContract) contract, selectedVehicle);
        } else {
            throw new IllegalArgumentException("Unsupported contract type");
        }
    }


    // Updated saveLeaseContract method
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
                    return;
                }

                double totalPrice = leaseContract.getTotalPrice() + Vehicle.getPrice();
                double expectedEndingValue = totalPrice * 0.5;
                double leaseFee = totalPrice * 0.07;


                leaseContract.setLeaseFee(leaseFee);
                leaseContract.calculateMonthlyPayment();

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
                        + Vehicle.getPrice() + "|"
                        + expectedEndingValue + "|"
                        + leaseFee + "|"
                        + leaseContract.getMonthlyPayment());
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
                double totalPrice = salesContract.getTotalPrice() + Vehicle.getPrice();
                double initialAmount = totalPrice + salesContract.getSalesTaxAmount() +
                        salesContract.getRecordingFee() + salesContract.getProcessingFee();
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
                        + Vehicle.getPrice() + "|"
                        + totalPrice + "|"
                        + salesContract.getSalesTaxAmount() + "|"
                        + salesContract.getRecordingFee() + "|"
                        + salesContract.getProcessingFee() + "|"
                        + (salesContract.isFinance() ? "YES" : "NO") + "|"
                        + (salesContract.isFinance() ? salesContract.getMonthlyPayment() : "No finance") + "\n");
            } else {
                System.out.println("No vehicle selected.");
            }
        } catch (IOException e) {
            System.err.println("Error writing sales contract to file: " + e.getMessage());
        }
    }
}
