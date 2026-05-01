package com.carrental.ui;

import com.carrental.service.DatabaseConnection;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class NotificationView {

    private Stage stage;
    private String username;

    public NotificationView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
    }

    public void show() {

        ListView<String> list = new ListView<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT message FROM notification WHERE username = ? ORDER BY created_at DESC"
             )) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.getItems().add(rs.getString("message"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Button back = new Button("Back");
        back.setOnAction(e -> new WelcomeView(stage, username).show());

        VBox layout = new VBox(10, list, back);

        stage.setScene(new Scene(layout, 500, 400));
        stage.show();
    }
}