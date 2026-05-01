package com.carrental.ui;

import com.carrental.service.UserService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginView {

    private Stage stage;

    public LoginView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("Login");

        ImageView imageView = new ImageView();

        try {
            Image image = new Image(getClass().getResource("/login.jpg").toExternalForm());
            imageView.setImage(image);
            imageView.setFitWidth(280);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("login.jpg not found");
        }

        Label titleLabel = new Label("Login");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);

        Label messageLabel = new Label();

        Button loginButton = new Button("Log In");
        Button registerButton = new Button("Register");

        loginButton.setPrefSize(200, 40);
        registerButton.setPrefSize(200, 40);

        loginButton.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserService.validateLogin(username, password)) {

                String role = UserService.getUserRole(username);

                switch (role) {
                    case "ADMIN":
                        new AdminView(stage).show();
                        break;

                    case "RECEPTIONIST":
                        new ReceptionistView(stage).show();
                        break;

                    default:
                        new WelcomeView(stage, username).show();
                }

            } else {
                messageLabel.setText("Incorrect username or password.");
            }
        });

        registerButton.setOnAction(e -> {
            new RegisterView(stage).show();
        });

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        if (imageView.getImage() != null) {
            layout.getChildren().add(imageView);
        }

        layout.getChildren().addAll(
                titleLabel,
                usernameField,
                passwordField,
                loginButton,
                registerButton,
                messageLabel
        );

        Scene scene = new Scene(layout, 600, 600);
        StyleUtil.applyStyle(scene);
        stage.setScene(scene);
        stage.show();
    }
}