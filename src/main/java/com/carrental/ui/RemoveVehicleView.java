package com.carrental.ui;

import com.carrental.service.VehicleService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveVehicleView {

    private Stage stage;

    public RemoveVehicleView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        TextField barcode = new TextField();
        barcode.setPromptText("Enter Barcode");

        Label message = new Label();

        Button remove = new Button("Remove");
        Button back = new Button("Back");

        remove.setOnAction(e -> {
            boolean success = VehicleService.removeVehicle(barcode.getText());
            message.setText(success ? "Removed!" : "Not found");
        });

        back.setOnAction(e -> new AdminView(stage).show());

        VBox layout = new VBox(10, barcode, remove, back, message);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout, 400, 300));
        stage.show();
    }
}