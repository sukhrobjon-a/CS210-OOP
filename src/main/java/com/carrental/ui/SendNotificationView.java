package com.carrental.ui;

import com.carrental.service.NotificationService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SendNotificationView {

    private Stage stage;
    private String role;

    public SendNotificationView(Stage stage, String role) {
        this.stage = stage;
        this.role = role;
    }

    public void show() {

        stage.setTitle("Send Notification");

        Label title = new Label("Send Notification");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Receiver Username");
        usernameField.setMaxWidth(280);

        TextArea messageField = new TextArea();
        messageField.setPromptText("Message");
        messageField.setMaxWidth(350);
        messageField.setPrefHeight(120);

        Label statusLabel = new Label();

        Button sendButton = new Button("Send");
        Button backButton = new Button("Back");

        sendButton.setPrefSize(220, 45);
        backButton.setPrefSize(220, 45);

        sendButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String message = messageField.getText().trim();

            if (username.isEmpty() || message.isEmpty()) {
                statusLabel.setText("Fill all fields.");
                return;
            }

            NotificationService.sendNotification(username, message);
            statusLabel.setText("Notification sent.");
            messageField.clear();
        });

        backButton.setOnAction(e -> {
            if ("ADMIN".equals(role)) {
                new AdminView(stage).show();
            } else {
                new ReceptionistView(stage).show();
            }
        });

        VBox layout = new VBox(12);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                title,
                usernameField,
                messageField,
                sendButton,
                backButton,
                statusLabel
        );

        stage.setScene(new Scene(layout, 600, 500));
        stage.show();
    }
}