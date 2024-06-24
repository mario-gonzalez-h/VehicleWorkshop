package org.example.app;

import org.example.controller.VehiclesController;
import org.example.controller.SalesContractsController;
import org.example.controller.LeaseContractsController;
import org.example.model.Vehicle;
import org.example.model.SalesContract;
import org.example.model.LeaseContract;

import java.sql.Date;

public class DealershipApplication {

    public static void main(String[] args) {
        VehiclesController vehiclesController = new VehiclesController();
        SalesContractsController salesContractsController = new SalesContractsController();
        LeaseContractsController leaseContractsController = new LeaseContractsController();

        System.out.println("All Vehicles:");
        vehiclesController.getAllVehicles().forEach(System.out::println);

        System.out.println("\nVehicle with VIN 12345:");
        System.out.println(vehiclesController.getVehicleByVIN(12345));

        System.out.println("\nVehicles with specific criteria:");
        vehiclesController.getVehicles(20000.0, 50000.0, "Toyota", "Camry", 2010, 2022, "Red", 0, 50000, "Sedan").forEach(System.out::println);

        System.out.println("\nAdding a new Vehicle:");
        Vehicle newVehicle = new Vehicle(67890, 2023, "BMW", "X5", "SUV", "Black", 10, 60000.0, 1);
        vehiclesController.addVehicle(newVehicle);

        System.out.println("\nUpdating the Vehicle:");
        newVehicle.setPrice(59000.0);
        vehiclesController.updateVehicle(newVehicle);

        System.out.println("\nDeleting the Vehicle:");
        vehiclesController.deleteVehicle(67890);

        System.out.println("\nSales Contracts for Dealership ID 1 between 2024-01-01 and 2024-12-31:");
        salesContractsController.getSalesInformationWithDateRange(1, Date.valueOf("2024-01-01"), Date.valueOf("2024-12-31")).forEach(System.out::println);

        System.out.println("\nLease Contracts for Dealership ID 2 between 2024-01-01 and 2024-12-31:");
        leaseContractsController.getLeaseInformationWithDateRange(2, Date.valueOf("2024-01-01"), Date.valueOf("2024-12-31")).forEach(System.out::println);
    }
}
