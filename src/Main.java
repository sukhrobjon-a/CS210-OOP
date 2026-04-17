/*
import java.time.LocalDateTime;
import java.util.*;

// Enums
enum VehicleType {
    CAR, TRUCK, SUV, VAN, MOTORCYCLE
}

enum ReservationStatus {
    ACTIVE, COMPLETED, CANCELLED
}

enum AccountType {
    MEMBER, RECEPTIONIST, WORKER
}

// Location Class
class CarRentalLocation {
    private String name;
    private String address;
    private List<Vehicle> vehicles;

    public CarRentalLocation(String name, String address) {
        this.name = name;
        this.address = address;
        this.vehicles = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public List<Vehicle> getVehicles() { return vehicles; }
    public void addVehicle(Vehicle vehicle) { vehicles.add(vehicle); }
}

// Vehicle Class
class Vehicle {
    private String barcode;
    private String licensePlate;
    private VehicleType type;
    private String make;
    private String model;
    private int passengerCapacity;
    private double mileage;
    private String parkingStall;
    private boolean available;
    private VehicleLog log;

    public Vehicle(String barcode, String licensePlate, VehicleType type,
                   String make, String model, int passengerCapacity, String parkingStall) {
        this.barcode = barcode;
        this.licensePlate = licensePlate;
        this.type = type;
        this.make = make;
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.parkingStall = parkingStall;
        this.available = true;
        this.log = new VehicleLog(barcode);
    }

    public String getBarcode() { return barcode; }
    public String getLicensePlate() { return licensePlate; }
    public VehicleType getType() { return type; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public VehicleLog getLog() { return log; }
    public String getParkingStall() { return parkingStall; }
}

// Account Classes
abstract class Account {
    protected String id;
    protected String name;
    protected String email;
    protected AccountType type;

    public Account(String id, String name, String email, AccountType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

class Member extends Account {
    private double balance;
    private List<VehicleReservation> reservations;

    public Member(String id, String name, String email) {
        super(id, name, email, AccountType.MEMBER);
        this.balance = 0;
        this.reservations = new ArrayList<>();
    }

    public void addReservation(VehicleReservation reservation) {
        reservations.add(reservation);
    }

    public List<VehicleReservation> getReservations() { return reservations; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}

class Receptionist extends Account {
    public Receptionist(String id, String name, String email) {
        super(id, name, email, AccountType.RECEPTIONIST);
    }
}

class Worker extends Account {
    public Worker(String id, String name, String email) {
        super(id, name, email, AccountType.WORKER);
    }
}

// VehicleReservation Class
class VehicleReservation {
    private String reservationId;
    private Member member;
    private Vehicle vehicle;
    private LocalDateTime pickUpDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private ReservationStatus status;
    private List<Equipment> equipment;
    private List<Service> services;
    private RentalInsurance insurance;
    private Bill bill;

    public VehicleReservation(String reservationId, Member member, Vehicle vehicle,
                              LocalDateTime pickUpDate, LocalDateTime dueDate) {
        this.reservationId = reservationId;
        this.member = member;
        this.vehicle = vehicle;
        this.pickUpDate = pickUpDate;
        this.dueDate = dueDate;
        this.status = ReservationStatus.ACTIVE;
        this.equipment = new ArrayList<>();
        this.services = new ArrayList<>();
        this.bill = new Bill();
    }

    public void addEquipment(Equipment eq) { equipment.add(eq); }
    public void addService(Service service) { services.add(service); }
    public void setInsurance(RentalInsurance insurance) { this.insurance = insurance; }
    public void completeReservation(LocalDateTime returnDate) {
        this.returnDate = returnDate;
        this.status = ReservationStatus.COMPLETED;
    }

    public String getReservationId() { return reservationId; }
    public Member getMember() { return member; }
    public Vehicle getVehicle() { return vehicle; }
    public LocalDateTime getDueDate() { return dueDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
    public Bill getBill() { return bill; }
    public ReservationStatus getStatus() { return status; }
}

// Equipment, Service, and Insurance Classes
class Equipment {
    private String name;
    private double price;

    public Equipment(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class Service {
    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class RentalInsurance {
    private String type;
    private double price;
    private String coverage;

    public RentalInsurance(String type, double price, String coverage) {
        this.type = type;
        this.price = price;
        this.coverage = coverage;
    }

    public double getPrice() { return price; }
}

// VehicleLog Class
class VehicleLog {
    private String vehicleBarcode;
    private List<String> logEntries;

    public VehicleLog(String vehicleBarcode) {
        this.vehicleBarcode = vehicleBarcode;
        this.logEntries = new ArrayList<>();
    }

    public void addEntry(String entry) {
        logEntries.add(LocalDateTime.now() + " - " + entry);
    }

    public List<String> getLogEntries() { return logEntries; }
}

// Bill Class
class Bill {
    private List<String> items;
    private double total;

    public Bill() {
        this.items = new ArrayList<>();
        this.total = 0;
    }

    public void addItem(String item, double price) {
        items.add(item + ": $" + price);
        total += price;
    }

    public double getTotal() { return total; }
    public List<String> getItems() { return items; }
}

// Notification Class
class Notification {
    private String message;
    private Member member;
    private LocalDateTime sentTime;

    public Notification(Member member, String message) {
        this.member = member;
        this.message = message;
        this.sentTime = LocalDateTime.now();
    }

    public void send() {
        System.out.println("Notification sent to " + member.getName() + ": " + message);
    }
}

// Main CarRentalSystem Class
class CarRentalSystem {
    private List<CarRentalLocation> locations;
    private List<Member> members;
    private List<VehicleReservation> reservations;

    public CarRentalSystem() {
        this.locations = new ArrayList<>();
        this.members = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addLocation(CarRentalLocation location) {
        locations.add(location);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public boolean searchVehicle(VehicleType type) {
        for (CarRentalLocation location : locations) {
            for (Vehicle vehicle : location.getVehicles()) {
                if (vehicle.getType() == type && vehicle.isAvailable()) {
                    return true;
                }
            }
        }
        return false;
    }

    public VehicleReservation reserveVehicle(Member member, Vehicle vehicle,
                                             LocalDateTime pickUpDate, LocalDateTime dueDate) {
        if (!vehicle.isAvailable()) return null;

        String reservationId = UUID.randomUUID().toString();
        VehicleReservation reservation = new VehicleReservation(reservationId, member, vehicle,
                pickUpDate, dueDate);
        vehicle.setAvailable(false);
        reservations.add(reservation);
        member.addReservation(reservation);
        vehicle.getLog().addEntry("Vehicle reserved");
        return reservation;
    }

    public void checkOutVehicle(VehicleReservation reservation) {
        reservation.getVehicle().getLog().addEntry("Vehicle checked out");
        System.out.println("Vehicle checked out successfully");
    }

    public void returnVehicle(VehicleReservation reservation, LocalDateTime returnDate) {
        Vehicle vehicle = reservation.getVehicle();
        vehicle.getLog().addEntry("Vehicle returned");

        Bill bill = reservation.getBill();
        bill.addItem("Rental Fee", 50.0);

        if (returnDate.isAfter(reservation.getDueDate())) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(
                    reservation.getDueDate(), returnDate);
            double lateFee = daysLate * 25.0;
            bill.addItem("Late Fee", lateFee);
        }

        reservation.completeReservation(returnDate);
        vehicle.setAvailable(true);
        System.out.println("Vehicle returned. Total bill: $" + bill.getTotal());
    }

    public void cancelReservation(VehicleReservation reservation) {
        reservation.getVehicle().setAvailable(true);
        reservation.status = ReservationStatus.CANCELLED;
        System.out.println("Reservation cancelled");
    }
}

// Main Method
public class Main {
    public static void main(String[] args) {
        CarRentalSystem system = new CarRentalSystem();

        // Create location
        CarRentalLocation location = new CarRentalLocation("Downtown", "123 Main St");
        system.addLocation(location);

        // Create vehicles
        Vehicle car = new Vehicle("BC001", "ABC123", VehicleType.CAR, "Toyota", "Camry", 5, "A1");
        location.addVehicle(car);

        // Create member
        Member member = new Member("M001", "John Doe", "john@example.com");
        system.addMember(member);

        // Reserve vehicle
        LocalDateTime pickUp = LocalDateTime.now().plusDays(1);
        LocalDateTime dueDate = LocalDateTime.now().plusDays(5);

        VehicleReservation reservation = system.reserveVehicle(member, car, pickUp, dueDate);

        if (reservation != null) {
            reservation.addEquipment(new Equipment("GPS", 10.0));
            reservation.addService(new Service("Additional Driver", 15.0));
            reservation.setInsurance(new RentalInsurance("Full Coverage", 30.0, "Comprehensive"));

            system.checkOutVehicle(reservation);
            system.returnVehicle(reservation, LocalDateTime.now().plusDays(6));
        }
    }
}
*/
