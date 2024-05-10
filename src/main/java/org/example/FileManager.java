package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<Dealership> getDealership(){

        List<Dealership> dealerships = new ArrayList<>();

        String filePath = "src/main/resources/inventory.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String line;
            while((line = reader.readLine()) != null){

                String[] data = line.split("\\|");

                if (data.length <= 3) {
                    String name = data[0];
                    String address = data[1];
                    String phoneNumber = data[2];

                    Dealership dealership = new Dealership(name, address, phoneNumber);
                    dealerships.add(dealership);
                }

            }
        }catch(IOException ex){
            System.out.println("Whoops, can't read that dealership!");
        }

        return dealerships;
    }

    public static void displayDealership(List<Dealership> dealerships){

        for (Dealership dealership : dealerships){
            System.out.printf("%s, %s, %s \n", dealership.getName(), dealership.getAddress(), dealership.getPhone());
        }

    }

    public static List<Vehicle> getVehicles(){

        List<Vehicle> vehicles = new ArrayList<>();

        String filePath = "src/main/resources/inventory.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String line;
            while((line = reader.readLine()) != null){

                String[] data = line.split("\\|");

                if (data.length > 3) {
                    int vin = Integer.parseInt(data[0]);
                    int year = Integer.parseInt(data[1]);
                    String make = data[2];
                    String model = data[3];
                    String vehicleType = data[4];
                    String color = data[5];
                    int odometer = Integer.parseInt(data[6]);
                    double price = Double.parseDouble(data[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    vehicles.add(vehicle);
                }


            }
        }catch(IOException ex){
            System.out.println("Whoops, can't read those vehicles!");
        }

        return vehicles;
    }

    public static void displayVehicles(List<Vehicle> vehicles){

        for (Vehicle vehicle : vehicles){
            System.out.printf("%d | %d | %s | %s | %s | %s | %d | $%.2f \n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        }
    }

}
