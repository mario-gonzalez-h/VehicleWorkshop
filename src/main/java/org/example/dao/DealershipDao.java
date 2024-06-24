package org.example.dao;

import org.example.config.DatabaseConfig;
import org.example.model.Dealership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {

    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String sql = "SELECT * FROM dealerships";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Dealership dealership = new Dealership(rs.getInt("DealershipID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"));
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    public Dealership findDealershipByVehicleVIN(int vin) {
        String sql = "CALL FindDealershipByVehicleVIN(?)";
        Dealership dealership = null;
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, vin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dealership = new Dealership(rs.getInt("DealershipID"), rs.getString("Name"),
                        rs.getString("Address"), rs.getString("Phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealership;
    }

    public List<Dealership> findDealershipsWithCarType(String make, String model, String color) {
        List<Dealership> dealerships = new ArrayList<>();
        String sql = "CALL FindDealershipsWithCarType(?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            stmt.setString(3, color);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dealership dealership = new Dealership(rs.getInt("DealershipID"), rs.getString("Name"),
                        rs.getString("Address"), rs.getString("Phone"));
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }
}
