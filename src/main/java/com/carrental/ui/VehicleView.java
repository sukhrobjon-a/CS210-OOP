package com.carrental.ui;

import com.carrental.model.Vehicle;
import com.carrental.service.VehicleService;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VehicleView {

    private Stage stage;
    private String role;

    public VehicleView(Stage stage, String role) {
        this.stage = stage;
        this.role = role;
    }

    public void show() {

        stage.setTitle("All Vehicles");

        TableView<Vehicle> table = new TableView<>();

        TableColumn<Vehicle, String> barcodeCol = new TableColumn<>("Barcode");
        barcodeCol.setPrefWidth(100);
        barcodeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getBarcode())
        );

        TableColumn<Vehicle, String> plateCol = new TableColumn<>("Plate");
        plateCol.setPrefWidth(110);
        plateCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getLicensePlate())
        );

        TableColumn<Vehicle, String> makeCol = new TableColumn<>("Make");
        makeCol.setPrefWidth(110);
        makeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getMake())
        );

        TableColumn<Vehicle, String> modelCol = new TableColumn<>("Model");
        modelCol.setPrefWidth(120);
        modelCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getModel())
        );

        TableColumn<Vehicle, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setPrefWidth(100);
        categoryCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory())
        );

        TableColumn<Vehicle, String> typeCol = new TableColumn<>("Type");
        typeCol.setPrefWidth(100);
        typeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getType())
        );

        TableColumn<Vehicle, String> priceCol = new TableColumn<>("Rate");
        priceCol.setPrefWidth(80);
        priceCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty("$" + data.getValue().getDailyRate())
        );

        TableColumn<Vehicle, String> statusCol = new TableColumn<>("Status");
        statusCol.setPrefWidth(100);
        statusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus())
        );

        table.getColumns().addAll(
                barcodeCol,
                plateCol,
                makeCol,
                modelCol,
                categoryCol,
                typeCol,
                priceCol,
                statusCol
        );

        table.setItems(FXCollections.observableArrayList(
                VehicleService.getAllVehicles()
        ));

        Button backButton = new Button("Back");
        backButton.setPrefSize(200, 45);

        backButton.setOnAction(e -> {
            if ("ADMIN".equals(role)) {
                new AdminView(stage).show();
            } else {
                new ReceptionistView(stage).show();
            }
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("All Vehicles"),
                table,
                backButton
        );

        stage.setScene(new Scene(layout, 900, 550));
        stage.show();
    }
}