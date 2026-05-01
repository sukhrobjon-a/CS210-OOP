package com.carrental.ui;

import com.carrental.service.UserService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddReceptionistView {

    private Stage stage;

    public AddReceptionistView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("Add Receptionist");

        Label title = new Label("Create Receptionist Account");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Receptionist Username");
        usernameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Receptionist Password");
        passwordField.setMaxWidth(250);

        Label messageLabel = new Label();

        Button createButton = new Button("Create Receptionist");
        Button backButton = new Button("Back");

        createButton.setPrefSize(220, 45);
        backButton.setPrefSize(220, 45);

        createButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Fill all fields.");
                return;
            }

            if (UserService.createReceptionist(username, password)) {
                messageLabel.setText("Receptionist created successfully.");
            } else {
                messageLabel.setText("Username already exists.");
            }
        });

        backButton.setOnAction(e -> new AdminView(stage).show());

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                createButton,
                backButton,
                messageLabel
        );

        stage.setScene(new Scene(layout, 600, 400));
        stage.show();
    }
}