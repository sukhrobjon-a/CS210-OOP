package com.carrental.ui;

import com.carrental.service.DatabaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AllReservationsView {

    private Stage stage;

    public AllReservationsView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("All Reservations");

        TableView<String> table = new TableView<>();

        TableColumn<String, String> reservationCol = new TableColumn<>("Reservation Details");
        reservationCol.setPrefWidth(1050);
        reservationCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue())
        );

        table.getColumns().add(reservationCol);

        ObservableList<String> reservations = FXCollections.observableArrayList();

        String query = """
                SELECT r.id, r.customer_name, v.barcode, v.make, v.model,
                       v.license_plate, r.start_date, r.due_date, r.return_date,
                       r.status, r.total_cost, r.late_fee
                FROM reservation r
                JOIN vehicle v ON r.vehicle_id = v.id
                ORDER BY r.created_at DESC
                """;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String row =
                        "Reservation ID: " + rs.getInt("id") +
                                " | User: " + rs.getString("customer_name") +
                                " | Barcode: " + rs.getString("barcode") +
                                " | Vehicle: " + rs.getString("make") + " " + rs.getString("model") +
                                " | Plate: " + rs.getString("license_plate") +
                                " | Start: " + rs.getString("start_date") +
                                " | Due: " + rs.getString("due_date") +
                                " | Return: " + rs.getString("return_date") +
                                " | Status: " + rs.getString("status") +
                                " | Total: $" + rs.getDouble("total_cost") +
                                " | Late Fee: $" + rs.getDouble("late_fee");

                reservations.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            reservations.add("Could not load reservations.");
        }

        if (reservations.isEmpty()) {
            reservations.add("No reservations found.");
        }

        table.setItems(reservations);

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 45);
        backButton.setOnAction(e -> new ReceptionistView(stage).show());

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("All Reservations"),
                table,
                backButton
        );

        stage.setScene(new Scene(layout, 1150, 550));
        stage.show();
    }
}