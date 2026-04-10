import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProfileCard extends Application {

    @Override
    public void start(Stage stage) {

        Label name = new Label("Suxrobjon Abdubannonov");
        name.setTextFill(javafx.scene.paint.Color.WHITE);
        name.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox top = new HBox(name);
        top.setPadding(new Insets(12));
        top.setAlignment(Pos.CENTER_LEFT);
        top.setStyle("-fx-background-color: #2C3E50;");



        Label deptLabel = new Label("Department:");
        Label deptValue = new Label("Cyber Security FC2");

        Label yearLabel = new Label("Year:");
        Label yearValue = new Label("1st Year");

        Label gpaLabel = new Label("GPA:");
        Label gpaValue = new Label("3.88");

        GridPane center = new GridPane();
        center.setHgap(10);
        center.setVgap(10);
        center.setPadding(new Insets(20));

        center.add(deptLabel, 0, 0);
        center.add(deptValue, 1, 0);

        center.add(yearLabel, 0, 1);
        center.add(yearValue, 1, 1);

        center.add(gpaLabel, 0, 2);
        center.add(gpaValue, 1, 2);



        Label bottom = new Label("New Uzbekistan University");
        bottom.setPadding(new Insets(8));
        bottom.setAlignment(Pos.CENTER);
        bottom.setMaxWidth(Double.MAX_VALUE);
        bottom.setStyle(
                "-fx-background-color: #ECF0F1; -fx-font-size: 13;"
        );

        HBox bottomBox = new HBox(bottom);
        bottomBox.setAlignment(Pos.CENTER);



        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 400, 250);

        stage.setTitle("Profile Card");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}