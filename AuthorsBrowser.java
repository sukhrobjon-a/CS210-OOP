package org.example;

import java.sql.*;
import java.util.Scanner;

public class AuthorsBrowser {

    public static void main(String[] args) {


        System.out.println("All authors:");

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Authors")) {

            while (rs.next()) {
                int id = rs.getInt("AuthorID");
                String first = rs.getString("FirstName");
                String last = rs.getString("LastName");

                System.out.println("ID: " + id + " | " + first + " " + last);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter last-name prefix to search: ");
        String prefix = scanner.nextLine();

        String sql = "SELECT * FROM Authors WHERE LastName LIKE ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, prefix + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;
                    int id = rs.getInt("AuthorID");
                    String first = rs.getString("FirstName");
                    String last = rs.getString("LastName");

                    System.out.println("ID: " + id + " | " + first + " " + last);
                }

                if (!found) {
                    System.out.println("No results found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        scanner.close();
    }
}