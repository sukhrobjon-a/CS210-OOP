package com.carrental.ui;

import com.carrental.model.Vehicle;
import com.carrental.service.VehicleService;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ReservationView {

    private Stage stage;
    private String username;

    private TableView<Vehicle> table;
    private Label message;

    public ReservationView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
    }

    public void show() {

        stage.setTitle("Reserve Vehicle");

        table = new TableView<>();

        TableColumn<Vehicle, String> makeCol = new TableColumn<>("Make");
        makeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getMake())
        );

        TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getModel())
        );

        TableColumn<Vehicle, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory())
        );

        TableColumn<Vehicle, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getType())
        );

        TableColumn<Vehicle, String> priceCol = new TableColumn<>("Price/Day");
        priceCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty("$" + data.getValue().getDailyRate())
        );

        TableColumn<Vehicle, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus())
        );

        table.getColumns().addAll(
                makeCol,
                modelCol,
                categoryCol,
                typeCol,
                priceCol,
                statusCol
        );

        refreshTable();

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("ALL", "Economy", "Standard", "Premium");
        categoryBox.setValue("ALL");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("ALL", "Car", "SUV", "Truck", "Van", "Motorcycle");
        typeBox.setValue("ALL");

        Button filterButton = new Button("Filter");
        Button clearFilterButton = new Button("Clear Filter");
        Button continueButton = new Button("Continue to Order Details");
        Button backButton = new Button("Back");

        filterButton.setPrefSize(220, 40);
        clearFilterButton.setPrefSize(220, 40);
        continueButton.setPrefSize(220, 45);
        backButton.setPrefSize(220, 45);

        message = new Label();

        filterButton.setOnAction(e -> {
            List<Vehicle> filteredVehicles = VehicleService.filterVehicles(
                    categoryBox.getValue(),
                    typeBox.getValue()
            );

            table.setItems(FXCollections.observableArrayList(filteredVehicles));
            message.setText("Filtered vehicles loaded.");
        });

        clearFilterButton.setOnAction(e -> {
            categoryBox.setValue("ALL");
            typeBox.setValue("ALL");
            refreshTable();
            message.setText("Filter cleared.");
        });

        continueButton.setOnAction(e -> {
            Vehicle selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                message.setText("Please select a vehicle first.");
                return;
            }

            new OrderDetailsView(stage, username, selected).show();
        });

        backButton.setOnAction(e -> {
            new WelcomeView(stage, username).show();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        layout.getChildren().addAll(
                new Label("Available Vehicles"),
                table,

                new Label("Vehicle Category"),
                categoryBox,

                new Label("Vehicle Type"),
                typeBox,

                filterButton,
                clearFilterButton,
                continueButton,
                backButton,
                message
        );

        Scene scene = new Scene(layout, 900, 650);
        stage.setScene(scene);
        stage.show();
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(
                VehicleService.getAvailableVehicles()
        ));
    }
}