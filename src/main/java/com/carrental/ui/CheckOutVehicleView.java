package com.carrental.ui;

import com.carrental.service.VehicleService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckOutVehicleView {

    private Stage stage;

    public CheckOutVehicleView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("Check Out Vehicle");

        Label title = new Label("Check Out Vehicle");
        Label instruction = new Label("Enter the vehicle barcode and username to check out a reserved vehicle.");

        TextField barcodeField = new TextField();
        barcodeField.setPromptText("Vehicle Barcode");
        barcodeField.setMaxWidth(280);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Customer Username");
        usernameField.setMaxWidth(280);

        Label messageLabel = new Label();

        Button checkOutButton = new Button("Check Out");
        Button backButton = new Button("Back");

        checkOutButton.setPrefSize(220, 45);
        backButton.setPrefSize(220, 45);

        checkOutButton.setOnAction(e -> {
            String barcode = barcodeField.getText().trim();
            String username = usernameField.getText().trim();

            if (barcode.isEmpty() || username.isEmpty()) {
                messageLabel.setText("Please fill all fields.");
                return;
            }

            String result = VehicleService.checkOutVehicle(barcode, username);
            messageLabel.setText(result);
        });

        backButton.setOnAction(e -> new ReceptionistView(stage).show());

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                title,
                instruction,
                barcodeField,
                usernameField,
                checkOutButton,
                backButton,
                messageLabel
        );

        stage.setScene(new Scene(layout, 600, 450));
        stage.show();
    }
}