package com.carrental.ui;

import com.carrental.service.VehicleService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptionistReturnView {

    private Stage stage;

    public ReceptionistReturnView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        stage.setTitle("Receptionist Return Vehicle");

        Label title = new Label("Return Vehicle");
        Label instruction = new Label("Enter barcode and customer username to return a vehicle.");

        TextField barcodeField = new TextField();
        barcodeField.setPromptText("Vehicle Barcode");
        barcodeField.setMaxWidth(280);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Customer Username");
        usernameField.setMaxWidth(280);

        Label messageLabel = new Label();

        Button returnButton = new Button("Return Vehicle");
        Button backButton = new Button("Back");

        returnButton.setPrefSize(220, 45);
        backButton.setPrefSize(220, 45);

        returnButton.setOnAction(e -> {
            String barcode = barcodeField.getText().trim();
            String username = usernameField.getText().trim();

            if (barcode.isEmpty() || username.isEmpty()) {
                messageLabel.setText("Please fill all fields.");
                return;
            }

            String result = VehicleService.returnVehicle(barcode, username);
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
                returnButton,
                backButton,
                messageLabel
        );

        stage.setScene(new Scene(layout, 600, 450));
        stage.show();
    }
}