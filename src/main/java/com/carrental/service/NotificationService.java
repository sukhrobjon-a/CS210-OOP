package com.carrental.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NotificationService {

    public static void sendNotification(String username, String message) {

        String query = "INSERT INTO notification (username, message) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, message);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}