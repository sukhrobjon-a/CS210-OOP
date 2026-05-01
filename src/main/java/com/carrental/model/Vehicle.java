package com.carrental.model;

public class Vehicle {

    private int id;
    private String barcode;
    private String licensePlate;
    private String make;
    private String model;
    private int capacity;
    private double mileage;
    private String category;
    private String type;
    private String parkingStall;
    private double dailyRate;
    private int fuelLevel;
    private String status;
    private String imagePath;

    public Vehicle(int id,
                   String barcode,
                   String licensePlate,
                   String make,
                   String model,
                   int capacity,
                   double mileage,
                   String category,
                   String type,
                   String parkingStall,
                   double dailyRate,
                   int fuelLevel,
                   String status,
                   String imagePath) {

        this.id = id;
        this.barcode = barcode;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.mileage = mileage;
        this.category = category;
        this.type = type;
        this.parkingStall = parkingStall;
        this.dailyRate = dailyRate;
        this.fuelLevel = fuelLevel;
        this.status = status;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getBarcode() { return barcode; }
    public String getLicensePlate() { return licensePlate; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getCapacity() { return capacity; }
    public double getMileage() { return mileage; }
    public String getCategory() { return category; }
    public String getType() { return type; }
    public String getParkingStall() { return parkingStall; }
    public double getDailyRate() { return dailyRate; }
    public int getFuelLevel() { return fuelLevel; }
    public String getStatus() { return status; }
    public String getImagePath() { return imagePath; }
}