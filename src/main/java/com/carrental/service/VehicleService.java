package com.carrental.service;

import com.carrental.model.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class VehicleService {

    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> list = new ArrayList<>();

        String query = """
            SELECT v.*, vc.name AS category, vt.name AS type
            FROM vehicle v
            JOIN vehicle_category vc ON v.category_id = vc.id
            JOIN vehicle_type vt ON v.type_id = vt.id
        """;

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(createVehicle(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Vehicle> getAvailableVehicles() {
        List<Vehicle> list = new ArrayList<>();

        String query = """
            SELECT v.*, vc.name AS category, vt.name AS type
            FROM vehicle v
            JOIN vehicle_category vc ON v.category_id = vc.id
            JOIN vehicle_type vt ON v.type_id = vt.id
            WHERE v.status='Available'
        """;

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(createVehicle(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Vehicle> filterVehicles(String category, String type) {
        List<Vehicle> list = new ArrayList<>();

        StringBuilder query = new StringBuilder("""
            SELECT v.*, vc.name AS category, vt.name AS type
            FROM vehicle v
            JOIN vehicle_category vc ON v.category_id = vc.id
            JOIN vehicle_type vt ON v.type_id = vt.id
            WHERE v.status='Available'
        """);

        if (!category.equals("ALL")) {
            query.append(" AND vc.name='").append(category).append("'");
        }

        if (!type.equals("ALL")) {
            query.append(" AND vt.name='").append(type).append("'");
        }

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query.toString())) {

            while (rs.next()) {
                list.add(createVehicle(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean addVehicle(String barcode,
                                     String plate,
                                     String make,
                                     String model,
                                     String category,
                                     String type,
                                     double price,
                                     String imagePath) {

        try (Connection conn = DatabaseConnection.connect()) {

            int categoryId = getCategoryId(conn, category);
            int typeId = getTypeId(conn, type);

            String query = """
                INSERT INTO vehicle
                (barcode, license_plate, make, model,
                 category_id, type_id, daily_rate,
                 parking_stall, fuel_level, status, available, image_path)
                VALUES (?, ?, ?, ?, ?, ?, ?, 'A1', 100, 'Available', true, ?)
            """;

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, barcode);
            ps.setString(2, plate);
            ps.setString(3, make);
            ps.setString(4, model);
            ps.setInt(5, categoryId);
            ps.setInt(6, typeId);
            ps.setDouble(7, price);
            ps.setString(8, imagePath);

            ps.executeUpdate();

            int vehicleId = getVehicleIdByBarcode(conn, barcode);
            insertVehicleLog(conn, vehicleId, "Added", "Vehicle added to inventory.");

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeVehicle(String barcode) {
        try (Connection conn = DatabaseConnection.connect()) {

            int vehicleId = getVehicleIdByBarcode(conn, barcode);

            if (vehicleId == -1) {
                return false;
            }

            String deleteLogs = "DELETE FROM vehicle_log WHERE vehicle_id=?";
            try (PreparedStatement ps = conn.prepareStatement(deleteLogs)) {
                ps.setInt(1, vehicleId);
                ps.executeUpdate();
            }

            String deleteVehicle = "DELETE FROM vehicle WHERE barcode=?";
            try (PreparedStatement ps = conn.prepareStatement(deleteVehicle)) {
                ps.setString(1, barcode);
                return ps.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean reserveVehicle(int vehicleId,
                                         String username,
                                         String startDate,
                                         String dueDate,
                                         boolean insurance,
                                         String equipment,
                                         String service,
                                         double totalCost) {

        try (Connection conn = DatabaseConnection.connect()) {

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("""
                INSERT INTO reservation
                (vehicle_id, customer_name, start_date, due_date, status,
                 insurance, equipment, additional_service, total_cost)
                VALUES (?, ?, ?, ?, 'Reserved', ?, ?, ?, ?)
            """);

            ps.setInt(1, vehicleId);
            ps.setString(2, username);
            ps.setString(3, startDate);
            ps.setString(4, dueDate);
            ps.setBoolean(5, insurance);
            ps.setString(6, equipment);
            ps.setString(7, service);
            ps.setDouble(8, totalCost);
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(
                    "UPDATE vehicle SET status='Reserved', available=false WHERE id=?"
            );

            ps2.setInt(1, vehicleId);
            ps2.executeUpdate();

            insertVehicleLog(conn, vehicleId, "Reserved", "Vehicle reserved by " + username);

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String checkOutVehicle(String barcode, String username) {
        try (Connection conn = DatabaseConnection.connect()) {

            conn.setAutoCommit(false);

            int vehicleId = getVehicleIdByBarcode(conn, barcode);

            if (vehicleId == -1) {
                return "Vehicle not found.";
            }

            String findReservation = """
                SELECT id
                FROM reservation
                WHERE vehicle_id=? AND customer_name=? AND status='Reserved'
                ORDER BY created_at DESC
                LIMIT 1
            """;

            int reservationId;

            try (PreparedStatement ps = conn.prepareStatement(findReservation)) {
                ps.setInt(1, vehicleId);
                ps.setString(2, username);

                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    conn.rollback();
                    return "No active reserved reservation found for this user and vehicle.";
                }

                reservationId = rs.getInt("id");
                rs.close();
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE reservation SET status='CheckedOut' WHERE id=?"
            )) {
                ps.setInt(1, reservationId);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE vehicle SET status='Rented', available=false WHERE id=?"
            )) {
                ps.setInt(1, vehicleId);
                ps.executeUpdate();
            }

            insertVehicleLog(conn, vehicleId, "CheckedOut", "Vehicle checked out to " + username);

            conn.commit();

            return "Vehicle checked out successfully.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Check-out failed.";
        }
    }

    public static String returnVehicle(String barcode, String username) {
        try (Connection conn = DatabaseConnection.connect()) {

            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, daily_rate FROM vehicle WHERE barcode=?"
            );
            ps.setString(1, barcode);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                conn.rollback();
                return "Vehicle not found.";
            }

            int vehicleId = rs.getInt("id");
            double dailyRate = rs.getDouble("daily_rate");

            PreparedStatement ps2 = conn.prepareStatement("""
                SELECT id, due_date FROM reservation
                WHERE vehicle_id=? AND customer_name=? AND status IN ('Reserved', 'CheckedOut')
                ORDER BY created_at DESC LIMIT 1
            """);

            ps2.setInt(1, vehicleId);
            ps2.setString(2, username);

            ResultSet rs2 = ps2.executeQuery();

            if (!rs2.next()) {
                conn.rollback();
                return "No active reservation.";
            }

            int resId = rs2.getInt("id");
            LocalDate due = rs2.getDate("due_date").toLocalDate();

            LocalDate today = LocalDate.now();
            long lateDays = today.isAfter(due) ? ChronoUnit.DAYS.between(due, today) : 0;
            double lateFee = lateDays * dailyRate * 0.2;

            PreparedStatement ps3 = conn.prepareStatement("""
                UPDATE reservation
                SET return_date=?, status='Returned', late_fee=?
                WHERE id=?
            """);

            ps3.setDate(1, Date.valueOf(today));
            ps3.setDouble(2, lateFee);
            ps3.setInt(3, resId);
            ps3.executeUpdate();

            PreparedStatement ps4 = conn.prepareStatement(
                    "UPDATE vehicle SET status='Available', available=true WHERE id=?"
            );

            ps4.setInt(1, vehicleId);
            ps4.executeUpdate();

            insertVehicleLog(conn, vehicleId, "Returned",
                    "Vehicle returned by " + username + ". Late fee: $" + String.format("%.2f", lateFee));

            conn.commit();

            return "Returned successfully. Late fee: $" + String.format("%.2f", lateFee);

        } catch (Exception e) {
            e.printStackTrace();
            return "Return failed.";
        }
    }

    private static int getCategoryId(Connection conn, String name) throws Exception {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id FROM vehicle_category WHERE LOWER(name)=LOWER(?)"
        );

        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) return rs.getInt("id");

        throw new Exception("Category not found");
    }

    private static int getTypeId(Connection conn, String name) throws Exception {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id FROM vehicle_type WHERE LOWER(name)=LOWER(?)"
        );

        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) return rs.getInt("id");

        throw new Exception("Type not found");
    }

    private static int getVehicleIdByBarcode(Connection conn, String barcode) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT id FROM vehicle WHERE barcode=?");
        ps.setString(1, barcode);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }

        return -1;
    }

    private static void insertVehicleLog(Connection conn, int vehicleId, String eventType, String description) throws Exception {
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO vehicle_log (vehicle_id, event_type, description) VALUES (?, ?, ?)"
        );

        ps.setInt(1, vehicleId);
        ps.setString(2, eventType);
        ps.setString(3, description);
        ps.executeUpdate();
    }

    private static Vehicle createVehicle(ResultSet rs) throws Exception {
        return new Vehicle(
                rs.getInt("id"),
                rs.getString("barcode"),
                rs.getString("license_plate"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getInt("passenger_capacity"),
                rs.getDouble("mileage"),
                rs.getString("category"),
                rs.getString("type"),
                rs.getString("parking_stall"),
                rs.getDouble("daily_rate"),
                rs.getInt("fuel_level"),
                rs.getString("status"),
                rs.getString("image_path")
        );
    }
}