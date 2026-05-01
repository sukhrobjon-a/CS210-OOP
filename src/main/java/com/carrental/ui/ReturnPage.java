package com.carrental.ui;

import com.carrental.service.VehicleService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReturnPage {

    private Stage stage;
    private String username;

    public ReturnPage(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
    }

    public void show() {

        stage.setTitle("Return Vehicle");

        Label titleLabel = new Label("Return Vehicle");
        Label instructionLabel = new Label("Enter the barcode of the vehicle you want to return.");

        TextField barcodeField = new TextField();
        barcodeField.setPromptText("Vehicle Barcode");
        barcodeField.setMaxWidth(280);

        Label messageLabel = new Label();

        Button returnButton = new Button("Return Vehicle");
        Button backButton = new Button("Back");

        returnButton.setPrefSize(220, 45);
        backButton.setPrefSize(220, 45);

        returnButton.setOnAction(e -> {

            String barcode = barcodeField.getText().trim();

            if (barcode.isEmpty()) {
                messageLabel.setText("Please enter vehicle barcode.");
                return;
            }

            String result = VehicleService.returnVehicle(barcode, username);
            messageLabel.setText(result);
        });

        backButton.setOnAction(e -> new WelcomeView(stage, username).show());

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                titleLabel,
                instructionLabel,
                barcodeField,
                returnButton,
                backButton,
                messageLabel
        );

        Scene scene = new Scene(layout, 600, 450);
        stage.setScene(scene);
        stage.show();
    }
}