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

public class TitlesBrowser extends Application {

    private TableView<AuthorBook> table = new TableView<>();

    private TextField searchField = new TextField();

    @Override
    public void start(Stage stage) {

        TableColumn<AuthorBook, String> firstCol = new TableColumn<>("First Name");
        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<AuthorBook, String> lastCol = new TableColumn<>("Last Name");
        lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<AuthorBook, String> isbnCol = new TableColumn<>("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<AuthorBook, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(230);

        table.getColumns().addAll(firstCol, lastCol, isbnCol, titleCol);

        searchField.setPromptText("Last-name prefix");

        Button searchBtn = new Button("Search");

        searchBtn.setOnAction(e -> {
            String input = searchField.getText().trim();

            if (input.isEmpty()) {
                loadData("%");
            } else {
                loadData(input + "%");
            }
        });

        HBox topBox = new HBox(10, searchField, searchBtn);
        VBox root = new VBox(10, topBox, table);
        root.setPadding(new javafx.geometry.Insets(15));

        loadData("%");

        Scene scene = new Scene(root, 640, 400);

        stage.setTitle("Titles Browser");
        stage.setScene(scene);
        stage.show();
    }

    private void loadData(String pattern) {

        ObservableList<AuthorBook> list = FXCollections.observableArrayList();

        String sql =
                "SELECT a.FirstName, a.LastName, t.ISBN, t.Title " +
                        "FROM Authors a " +
                        "INNER JOIN AuthorISBN ai ON a.AuthorID = ai.AuthorID " +
                        "INNER JOIN Titles t ON ai.ISBN = t.ISBN " +
                        "WHERE a.LastName LIKE ? " +
                        "ORDER BY a.LastName, a.FirstName";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pattern);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new AuthorBook(
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("ISBN"),
                        rs.getString("Title")
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