package com.carrental.ui;

import com.carrental.model.Vehicle;
import com.carrental.service.VehicleService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.temporal.ChronoUnit;

public class OrderDetailsView {

    private Stage stage;
    private String username;
    private Vehicle vehicle;

    private DatePicker startDatePicker;
    private DatePicker dueDatePicker;
    private CheckBox insuranceBox;
    private CheckBox gpsBox;
    private CheckBox roadsideBox;
    private Label totalLabel;
    private Label messageLabel;

    public OrderDetailsView(Stage stage, String username, Vehicle vehicle) {
        this.stage = stage;
        this.username = username;
        this.vehicle = vehicle;
    }

    public void show() {
        stage.setTitle("Order Details");

        Label titleLabel = new Label("Order Details");

        ImageView carImageView = new ImageView();

        try {
            Image image = new Image(getClass().getResource("/" + vehicle.getImagePath()).toExternalForm());
            carImageView.setImage(image);
            carImageView.setFitWidth(220);
            carImageView.setFitHeight(140);
            carImageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("Image not found: " + vehicle.getImagePath());
        }

        Label vehicleInfoLabel = new Label(
                "Vehicle: " + vehicle.getMake() + " " + vehicle.getModel() + "\n" +
                        "Type: " + vehicle.getType() + "\n" +
                        "Category: " + vehicle.getCategory() + "\n" +
                        "License Plate: " + vehicle.getLicensePlate() + "\n" +
                        "Daily Rate: $" + vehicle.getDailyRate() + "\n" +
                        "Parking Stall: " + vehicle.getParkingStall()
        );

        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");

        dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Due Date");

        insuranceBox = new CheckBox("Add Insurance ($20)");
        gpsBox = new CheckBox("Add GPS ($10)");
        roadsideBox = new CheckBox("Add Roadside Assistance ($15)");

        TextField cardNameField = new TextField();
        cardNameField.setPromptText("Cardholder Name");
        cardNameField.setMaxWidth(280);

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("Card Number");
        cardNumberField.setMaxWidth(280);

        TextField expiryField = new TextField();
        expiryField.setPromptText("Expiry Date MM/YY");
        expiryField.setMaxWidth(280);

        PasswordField cvvField = new PasswordField();
        cvvField.setPromptText("CVV");
        cvvField.setMaxWidth(280);

        totalLabel = new Label("Total: $0.00");
        messageLabel = new Label();

        Button calculateButton = new Button("Calculate Total");
        Button confirmButton = new Button("Confirm Reservation");
        Button backButton = new Button("Back");

        calculateButton.setPrefSize(220, 40);
        confirmButton.setPrefSize(220, 40);
        backButton.setPrefSize(220, 40);

        calculateButton.setOnAction(e -> {
            if (!datesAreValid()) return;
            double total = calculateTotal();
            totalLabel.setText("Total: $" + String.format("%.2f", total));
            messageLabel.setText("");
        });

        confirmButton.setOnAction(e -> {
            if (!datesAreValid()) return;

            if (cardNameField.getText().isEmpty() ||
                    cardNumberField.getText().isEmpty() ||
                    expiryField.getText().isEmpty() ||
                    cvvField.getText().isEmpty()) {
                messageLabel.setText("Please enter all card details.");
                return;
            }

            double total = calculateTotal();

            boolean success = VehicleService.reserveVehicle(
                    vehicle.getId(),
                    username,
                    startDatePicker.getValue().toString(),
                    dueDatePicker.getValue().toString(),
                    insuranceBox.isSelected(),
                    gpsBox.isSelected() ? "GPS" : "",
                    roadsideBox.isSelected() ? "Roadside Assistance" : "",
                    total
            );

            if (success) {
                messageLabel.setText("Reservation confirmed! Total paid: $" + String.format("%.2f", total));
                totalLabel.setText("Total: $" + String.format("%.2f", total));
            } else {
                messageLabel.setText("Reservation failed.");
            }
        });

        backButton.setOnAction(e -> new ReservationView(stage, username).show());

        VBox content = new VBox(8);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));

        if (carImageView.getImage() != null) {
            content.getChildren().add(carImageView);
        }

        content.getChildren().addAll(
                titleLabel,
                vehicleInfoLabel,
                new Label("Rental Dates"),
                startDatePicker,
                dueDatePicker,
                new Label("Extras"),
                insuranceBox,
                gpsBox,
                roadsideBox,
                new Label("Payment Details"),
                cardNameField,
                cardNumberField,
                expiryField,
                cvvField,
                calculateButton,
                totalLabel,
                confirmButton,
                backButton,
                messageLabel
        );

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 700, 650);
        stage.setScene(scene);
        stage.show();
    }

    private boolean datesAreValid() {
        if (startDatePicker.getValue() == null || dueDatePicker.getValue() == null) {
            messageLabel.setText("Please select start and due dates.");
            return false;
        }

        if (!dueDatePicker.getValue().isAfter(startDatePicker.getValue())) {
            messageLabel.setText("Due date must be after start date.");
            return false;
        }

        return true;
    }

    private double calculateTotal() {
        long days = ChronoUnit.DAYS.between(startDatePicker.getValue(), dueDatePicker.getValue());
        double total = days * vehicle.getDailyRate();

        if (insuranceBox.isSelected()) total += 20;
        if (gpsBox.isSelected()) total += 10;
        if (roadsideBox.isSelected()) total += 15;

        return total;
    }
}