package com.carrental;

import com.carrental.model.Vehicle;
import com.carrental.service.VehicleService;

import java.util.List;

public class TestDB {
    public static void main(String[] args) {

        List<Vehicle> vehicles = VehicleService.getAllVehicles();

        for (Vehicle v : vehicles) {
            System.out.println(v.getMake() + " " + v.getModel() + " - " + v.getCategory());
        }
    }
}