import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TrafficLight extends Application {

    @Override
    public void start(Stage stage) {

        Circle red = new Circle(30);
        red.setFill(Color.RED);
        red.setStroke(Color.DARKGRAY);
        red.setStrokeWidth(2);
        red.setOpacity(1.0);

        Circle yellow = new Circle(30);
        yellow.setFill(Color.YELLOW);
        yellow.setStroke(Color.DARKGRAY);
        yellow.setStrokeWidth(2);
        yellow.setOpacity(0.3);

        Circle green = new Circle(30);
        green.setFill(Color.GREEN);
        green.setStroke(Color.DARKGRAY);
        green.setStrokeWidth(2);
        green.setOpacity(0.3);

        Text label = new Text("Stop");
        label.setFill(Color.WHITE);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new javafx.geometry.Insets(20));
        root.getChildren().addAll(red, yellow, green, label);

        Scene scene = new Scene(root, 200, 300);
        scene.setFill(Color.DARKGRAY);

        stage.setTitle("Traffic Light");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}