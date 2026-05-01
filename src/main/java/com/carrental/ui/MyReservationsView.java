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

public class MyReservationsView {

    private Stage stage;
    private String username;

    public MyReservationsView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
    }

    public void show() {
        stage.setTitle("My Active Reservations");

        TableView<String> table = new TableView<>();

        TableColumn<String, String> reservationCol = new TableColumn<>("Active Reserved Vehicles");
        reservationCol.setPrefWidth(850);
        reservationCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue())
        );

        table.getColumns().add(reservationCol);

        ObservableList<String> reservations = FXCollections.observableArrayList();

        String query = """
                SELECT v.barcode, v.make, v.model, v.license_plate,
                       r.start_date, r.due_date, r.status, r.total_cost
                FROM reservation r
                JOIN vehicle v ON r.vehicle_id = v.id
                WHERE r.customer_name = ?
                  AND r.status = 'Reserved'
                ORDER BY r.created_at DESC
                """;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String row =
                        "Barcode: " + rs.getString("barcode") +
                                " | Vehicle: " + rs.getString("make") + " " + rs.getString("model") +
                                " | Plate: " + rs.getString("license_plate") +
                                " | Dates: " + rs.getString("start_date") + " to " + rs.getString("due_date") +
                                " | Status: " + rs.getString("status") +
                                " | Total: $" + rs.getDouble("total_cost");

                reservations.add(row);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            reservations.add("Could not load reservations.");
        }

        if (reservations.isEmpty()) {
            reservations.add("You have no active reservations.");
        }

        table.setItems(reservations);

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 45);
        backButton.setOnAction(e -> new WelcomeView(stage, username).show());

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("Active reservations for " + username),
                table,
                backButton
        );

        Scene scene = new Scene(layout, 950, 500);
        stage.setScene(scene);
        stage.show();
    }
}