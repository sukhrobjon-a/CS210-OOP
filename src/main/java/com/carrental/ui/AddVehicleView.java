package com.carrental.ui;

import com.carrental.service.VehicleService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddVehicleView {

    private Stage stage;

    public AddVehicleView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        TextField barcode = new TextField();
        barcode.setPromptText("Barcode");

        TextField plate = new TextField();
        plate.setPromptText("License Plate");

        TextField make = new TextField();
        make.setPromptText("Make");

        TextField model = new TextField();
        model.setPromptText("Model");

        TextField category = new TextField();
        category.setPromptText("Category (Economy/Standard/Premium)");

        TextField type = new TextField();
        type.setPromptText("Type (Car/SUV/etc)");

        TextField price = new TextField();
        price.setPromptText("Daily Rate");

        TextField image = new TextField();
        image.setPromptText("Image Path (cars/xxx.jpg)");

        Label message = new Label();

        Button add = new Button("Add Vehicle");
        Button back = new Button("Back");

        add.setOnAction(e -> {
            boolean success = VehicleService.addVehicle(
                    barcode.getText(),
                    plate.getText(),
                    make.getText(),
                    model.getText(),
                    category.getText(),
                    type.getText(),
                    Double.parseDouble(price.getText()),
                    image.getText()
            );

            message.setText(success ? "Vehicle Added!" : "Error adding vehicle");
        });

        back.setOnAction(e -> new AdminView(stage).show());

        VBox layout = new VBox(10, barcode, plate, make, model, category, type, price, image, add, back, message);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout, 500, 500));
        stage.show();
    }
}