import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class ExpenseTracker extends Application {

    private TextField categoryField;
    private TextField amountField;
    private TextField noteField;
    private Label statusLabel;
    private TextArea summaryArea;

    private final String FILE_NAME = "expenses.txt";

    @Override
    public void start(Stage stage) {

        categoryField = new TextField();
        amountField = new TextField();
        noteField = new TextField();

        Label categoryLabel = new Label("Category:");
        Label amountLabel = new Label("Amount:");
        Label noteLabel = new Label("Note:");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.setPadding(new Insets(20));

        form.add(categoryLabel, 0, 0);
        form.add(categoryField, 1, 0);

        form.add(amountLabel, 0, 1);
        form.add(amountField, 1, 1);

        form.add(noteLabel, 0, 2);
        form.add(noteField, 1, 2);


        Button addButton = new Button("Add Expense");
        Button showButton = new Button("Show Summary");
        Button clearButton = new Button("Clear Fields");


        statusLabel = new Label();


        summaryArea = new TextArea();
        summaryArea.setEditable(false);
        summaryArea.setPrefHeight(200);




        addButton.setOnAction(e -> addExpense());
        showButton.setOnAction(e -> showSummary());
        clearButton.setOnAction(e -> clearFields());



        VBox root = new VBox(12);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                form,
                addButton,
                showButton,
                clearButton,
                statusLabel,
                summaryArea
        );



        Scene scene = new Scene(root, 460, 420);
        stage.setTitle("Expense Tracker");
        stage.setScene(scene);
        stage.show();
    }

    private void addExpense() {
        String category = categoryField.getText().trim();
        String amountText = amountField.getText().trim();
        String note = noteField.getText().trim();

        if (category.isEmpty() || amountText.isEmpty()) {
            showError("Category and Amount are required!");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            showError("Invalid amount format!");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(category + "|" + amount + "|" + note);
            writer.newLine();

            showSuccess("Saved!");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                statusLabel.setText("");
                clearFields();
            });
            pause.play();

        } catch (IOException e) {
            showError("Error saving data!");
        }
    }

    private void showSummary() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            summaryArea.setText("No expenses found.");
            return;
        }

        double total = 0;
        StringBuilder summary = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length >= 2) {
                    String category = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String note = parts.length > 2 ? parts[2] : "";

                    total += amount;

                    summary.append(category)
                            .append(" --- $")
                            .append(String.format("%.2f", amount));

                    if (!note.isEmpty()) {
                        summary.append(" (").append(note).append(")");
                    }

                    summary.append("\n");
                }
            }

            summary.append("\nTotal: $")
                    .append(String.format("%.2f", total));

            summaryArea.setText(summary.toString());

        } catch (IOException | NumberFormatException e) {
            summaryArea.setText("Error reading file.");
        }
    }

    private void clearFields() {
        categoryField.clear();
        amountField.clear();
        noteField.clear();

    }

    private void showSuccess(String message) {
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText(message);
    }

    private void showError(String message) {
        statusLabel.setTextFill(Color.RED);
        statusLabel.setText(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}