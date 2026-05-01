package com.carrental.ui;

import com.carrental.model.User;
import com.carrental.service.UserService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {

    private Stage stage;

    public RegisterView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("Register");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField emailField = new TextField(); // optional, not saved
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        Label messageLabel = new Label();

        Button registerButton = new Button("Register");
        Button backButton = new Button("Back to Login");

        registerButton.setPrefSize(200, 40);
        backButton.setPrefSize(200, 40);

        registerButton.setOnAction(e -> {

            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (username.isEmpty() || email.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty()) {

                messageLabel.setText("All fields must be filled.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                messageLabel.setText("Passwords do not match.");
                return;
            }

            User newUser = new User(username, password, "RENTER");

            if (UserService.registerUser(newUser)) {
                messageLabel.setText("Registration successful!");
                new LoginView(stage).show();
            } else {
                messageLabel.setText("Username already exists.");
            }
        });

        backButton.setOnAction(e -> {
            new LoginView(stage).show();
        });

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                new Label("Username"), usernameField,
                new Label("Email"), emailField,
                new Label("Password"), passwordField,
                new Label("Confirm Password"), confirmPasswordField,
                registerButton,
                backButton,
                messageLabel
        );

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}