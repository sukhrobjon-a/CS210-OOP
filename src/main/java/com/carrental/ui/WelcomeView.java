package com.carrental.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WelcomeView {

    private Stage stage;
    private String username;

    public WelcomeView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
    }

    public void show() {

        stage.setTitle("Welcome");

        ImageView imageView = new ImageView();

        try {
            Image image = new Image(getClass().getResource("/welcome.jpg").toExternalForm());
            imageView.setImage(image);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("welcome.jpg not found");
        }

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Button reserveButton = new Button("Reserve Vehicle");
        Button returnButton = new Button("Return Vehicle");
        Button myReservationsButton = new Button("My Reservations");
        Button notificationsButton = new Button("Notifications");
        Button logoutButton = new Button("Log Out");

        reserveButton.setPrefSize(220, 50);
        returnButton.setPrefSize(220, 50);
        myReservationsButton.setPrefSize(220, 50);
        notificationsButton.setPrefSize(220, 50);
        logoutButton.setPrefSize(220, 50);

        reserveButton.setOnAction(e -> new ReservationView(stage, username).show());
        returnButton.setOnAction(e -> new ReturnPage(stage, username).show());
        myReservationsButton.setOnAction(e -> new MyReservationsView(stage, username).show());
        notificationsButton.setOnAction(e -> new NotificationView(stage, username).show());
        logoutButton.setOnAction(e -> new LoginView(stage).show());

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        if (imageView.getImage() != null) {
            layout.getChildren().add(imageView);
        }

        layout.getChildren().addAll(
                welcomeLabel,
                reserveButton,
                returnButton,
                myReservationsButton,
                notificationsButton,
                logoutButton
        );

        Scene scene = new Scene(layout, 600, 650);
        StyleUtil.applyStyle(scene);
        stage.setScene(scene);
        stage.show();
    }
}