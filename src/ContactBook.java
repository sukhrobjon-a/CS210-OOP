import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactBook extends Application {

    private Stage stage;

    private ListView<String> listView;
    private Label errorLabel;

    private final String FILE_NAME = "contacts.txt";

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Contact Book");

        showListScene();
        stage.show();
    }


    private void showListScene() {

        listView = new ListView<>();
        errorLabel = new Label();

        Button addBtn = new Button("Add New");
        Button deleteBtn = new Button("Delete Selected");
        Button refreshBtn = new Button("Refresh");

        addBtn.setOnAction(e -> showAddScene());
        deleteBtn.setOnAction(e -> deleteSelected());
        refreshBtn.setOnAction(e -> loadContacts());

        VBox root = new VBox(12, listView, addBtn, deleteBtn, refreshBtn, errorLabel);
        root.setPadding(new Insets(20));

        loadContacts();

        Scene scene = new Scene(root, 480, 380);
        stage.setScene(scene);
    }

    private void loadContacts() {
        listView.getItems().clear();
        errorLabel.setText("");

        File file = new File(FILE_NAME);

        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length == 3) {
                    String formatted = parts[0] + " --- " + parts[1] + "@" + parts[2];
                    listView.getItems().add(formatted);
                }
            }

        } catch (IOException e) {
            showError("Error reading file.");
        }
    }

    private void deleteSelected() {
        int index = listView.getSelectionModel().getSelectedIndex();

        if (index == -1) {
            showError("Select a contact to delete.");
            return;
        }

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
                if (i != index) {
                    lines.add(line);
                }
                i++;
            }
        } catch (IOException e) {
            showError("Error reading file.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            showError("Error writing file.");
            return;
        }

        loadContacts();
    }




    private void showAddScene() {

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();

        Label error = new Label();

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setPadding(new Insets(20));

        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);

        form.add(new Label("Phone:"), 0, 1);
        form.add(phoneField, 1, 1);

        form.add(new Label("Email:"), 0, 2);
        form.add(emailField, 1, 2);

        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");

        saveBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                error.setStyle("-fx-text-fill: red;");
                error.setText("All fields are required.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(name + "|" + phone + "|" + email);
                writer.newLine();
            } catch (IOException ex) {
                error.setStyle("-fx-text-fill: red;");
                error.setText("Error saving contact.");
                return;
            }

            showListScene();
        });

        cancelBtn.setOnAction(e -> showListScene());

        VBox root = new VBox(12, form, saveBtn, cancelBtn, error);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 480, 380);
        stage.setScene(scene);
    }



    private void showError(String msg) {
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setText(msg);
    }

    public static void main(String[] args) {
        launch(args);
    }
}