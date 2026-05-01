package com.carrental.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView {

    private Stage stage;

    public AdminView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("Admin Panel");

        Label title = new Label("Admin Panel");
        title.getStyleClass().add("title");

        Button addReceptionist = new Button("Add Receptionist");
        Button viewUsers = new Button("View All Users");
        Button viewVehicles = new Button("View All Vehicles");
        Button addVehicle = new Button("Add Vehicle");
        Button removeVehicle = new Button("Remove Vehicle");
        Button sendNotification = new Button("Send Notification");
        Button logout = new Button("Logout");

        addReceptionist.setPrefSize(240, 45);
        viewUsers.setPrefSize(240, 45);
        viewVehicles.setPrefSize(240, 45);
        addVehicle.setPrefSize(240, 45);
        removeVehicle.setPrefSize(240, 45);
        sendNotification.setPrefSize(240, 45);
        logout.setPrefSize(240, 45);

        addReceptionist.setOnAction(e -> new AddReceptionistView(stage).show());
        viewUsers.setOnAction(e -> new ViewUsersView(stage).show());
        viewVehicles.setOnAction(e -> new VehicleView(stage, "ADMIN").show());
        addVehicle.setOnAction(e -> new AddVehicleView(stage).show());
        removeVehicle.setOnAction(e -> new RemoveVehicleView(stage).show());
        sendNotification.setOnAction(e -> new SendNotificationView(stage, "ADMIN").show());
        logout.setOnAction(e -> new LoginView(stage).show());

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("card");

        layout.getChildren().addAll(
                title,
                addReceptionist,
                viewUsers,
                viewVehicles,
                addVehicle,
                removeVehicle,
                sendNotification,
                logout
        );

        Scene scene = new Scene(layout, 600, 600);
        StyleUtil.applyStyle(scene);

        stage.setScene(scene);
        stage.show();
    }
}