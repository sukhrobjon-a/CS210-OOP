package com.carrental.ui;

import com.carrental.model.User;
import com.carrental.service.DatabaseConnection;
import com.carrental.service.UserService;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewUsersView {

    private Stage stage;

    public ViewUsersView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("All Users");

        TableView<User> table = new TableView<>();

        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setPrefWidth(150);
        usernameCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getUsername())
        );

        TableColumn<User, String> roleCol = new TableColumn<>("Role");
        roleCol.setPrefWidth(150);
        roleCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getRole())
        );

        TableColumn<User, String> bookedCarsCol = new TableColumn<>("Booked Car Barcodes");
        bookedCarsCol.setPrefWidth(350);
        bookedCarsCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        getBookedCars(data.getValue().getUsername())
                )
        );

        table.getColumns().addAll(usernameCol, roleCol, bookedCarsCol);
        table.setItems(FXCollections.observableArrayList(UserService.getAllUsers()));

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 45);
        backButton.setOnAction(e -> new AdminView(stage).show());

        VBox layout = new VBox(15);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.getChildren().addAll(
                new Label("All Registered Users and Their Bookings"),
                table,
                backButton
        );

        stage.setScene(new Scene(layout, 750, 500));
        stage.show();
    }

    private String getBookedCars(String username) {

        String query = """
                SELECT v.barcode
                FROM reservation r
                JOIN vehicle v ON r.vehicle_id = v.id
                WHERE r.customer_name = ?
                  AND r.status = 'Reserved'
                """;

        StringBuilder result = new StringBuilder();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (result.length() > 0) {
                    result.append(", ");
                }

                result.append(rs.getString("barcode"));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result.length() == 0) {
            return "None";
        }

        return result.toString();
    }
}