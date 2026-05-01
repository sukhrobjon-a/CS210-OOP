package com.carrental.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ReceptionistView {

    private Stage stage;

    public ReceptionistView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("Receptionist Panel");

        Label title = new Label("Receptionist Panel");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Button viewVehiclesButton = new Button("View All Vehicles");
        Button viewReservationsButton = new Button("View All Reservations");
        Button checkOutButton = new Button("Check Out Vehicle");
        Button returnVehicleButton = new Button("Return Vehicle");
        Button sendNotificationButton = new Button("Send Notification");
        Button logoutButton = new Button("Logout");

        viewVehiclesButton.setPrefSize(240, 45);
        viewReservationsButton.setPrefSize(240, 45);
        checkOutButton.setPrefSize(240, 45);
        returnVehicleButton.setPrefSize(240, 45);
        sendNotificationButton.setPrefSize(240, 45);
        logoutButton.setPrefSize(240, 45);

        viewVehiclesButton.setOnAction(e -> new VehicleView(stage, "RECEPTIONIST").show());
        viewReservationsButton.setOnAction(e -> new AllReservationsView(stage).show());
        checkOutButton.setOnAction(e -> new CheckOutVehicleView(stage).show());
        returnVehicleButton.setOnAction(e -> new ReceptionistReturnView(stage).show());
        sendNotificationButton.setOnAction(e -> new SendNotificationView(stage, "RECEPTIONIST").show());
        logoutButton.setOnAction(e -> new LoginView(stage).show());

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                title,
                viewVehiclesButton,
                viewReservationsButton,
                checkOutButton,
                returnVehicleButton,
                sendNotificationButton,
                logoutButton
        );

        stage.setScene(new Scene(layout, 600, 550));
        stage.show();
    }
}