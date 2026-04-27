package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class AuthorsManager extends Application {

    private TableView<Author> table = new TableView<>();

    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();

    @Override
    public void start(Stage stage) {

        TableColumn<Author, Integer> idCol = new TableColumn<>("AuthorID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("authorID"));

        TableColumn<Author, String> firstCol = new TableColumn<>("FirstName");
        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Author, String> lastCol = new TableColumn<>("LastName");
        lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table.getColumns().addAll(idCol, firstCol, lastCol);

        loadAuthors();

        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");

        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");

        addBtn.setOnAction(e -> {
            try (Connection conn = DatabaseUtil.getConnection()) {
                String sql = "INSERT INTO Authors (FirstName, LastName) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, firstNameField.getText());
                ps.setString(2, lastNameField.getText());
                ps.executeUpdate();

                firstNameField.clear();
                lastNameField.clear();
                loadAuthors();

            } catch (SQLException ex) {
                System.out.println("Database error: " + ex.getMessage());
            }
        });

        updateBtn.setOnAction(e -> {
            Author selected = table.getSelectionModel().getSelectedItem();

            if (selected != null) {
                try (Connection conn = DatabaseUtil.getConnection()) {
                    String sql = "UPDATE Authors SET FirstName=?, LastName=? WHERE AuthorID=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, firstNameField.getText());
                    ps.setString(2, lastNameField.getText());
                    ps.setInt(3, selected.getAuthorID());
                    ps.executeUpdate();

                    loadAuthors();

                } catch (SQLException ex) {
                    System.out.println("Database error: " + ex.getMessage());
                }
            }
        });


        deleteBtn.setOnAction(e -> {
            Author selected = table.getSelectionModel().getSelectedItem();

            if (selected != null) {
                try (Connection conn = DatabaseUtil.getConnection()) {
                    String sql = "DELETE FROM Authors WHERE AuthorID=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, selected.getAuthorID());
                    ps.executeUpdate();

                    loadAuthors();

                } catch (SQLException ex) {
                    System.out.println("Database error: " + ex.getMessage());
                }
            }
        });



        HBox inputBox = new HBox(10, firstNameField, lastNameField, addBtn, updateBtn, deleteBtn);

        VBox root = new VBox(10, table, inputBox);
        root.setPadding(new javafx.geometry.Insets(15));

        Scene scene = new Scene(root, 550, 400);

        stage.setTitle("Authors Manager");
        stage.setScene(scene);
        stage.show();
    }


    private void loadAuthors() {

        ObservableList<Author> list = FXCollections.observableArrayList();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Authors")) {

            while (rs.next()) {
                list.add(new Author(
                        rs.getInt("AuthorID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        table.getItems().setAll(list);
    }

    public static void main(String[] args) {
        launch(args);
    }
}