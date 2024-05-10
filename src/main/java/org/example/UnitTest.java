package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void testGetVehiclesByPrice(){
        // Arrange
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        List<Vehicle> inventory = dealership.getInventory();
        // Act
        List<Vehicle> result = dealership.getVehiclesByPrice(10, 20000);

        // Assert
        assertEquals(2,result.size());
    }

    @Test
    public void testGetVehiclesByMakeModel() {
        // Arrange
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        List<Vehicle> inventory = dealership.getInventory();

        // Act
        List<Vehicle> result = dealership.getVehiclesByMakeModel("j", "Ranger");

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testGetVehiclesByYear() {
        // Arrange
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        List<Vehicle> inventory = dealership.getInventory();

        // Act
        List<Vehicle> result = dealership.getVehiclesByYear(1993);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testGetVehiclesByColor(){
        //Arrange
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        List<Vehicle> inventory = dealership.getInventory();
        //Act
        List<Vehicle> result = dealership.getVehiclesByColor("Or");
        //Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testGetVehiclesByMileage(){
        //Arrange
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        List<Vehicle> inventory = dealership.getInventory();
        //Act
        List<Vehicle> result = dealership.getVehiclesByMileage(1,2000000);
        //Assert
        assertEquals(1, result.size());
    }

    @Test
    public void testGetVehiclesByType(){
        //Arrange
        Dealership dealership = new Dealership("D & B Used Cars", "111 Old Benbrook Rd", "817-555-5555");
        List<Vehicle> inventory = dealership.getInventory();
        //Act
        List<Vehicle> result = dealership.getVehiclesByType("SUV");
        //Assert
        assertEquals(1, result.size());
    }

}
