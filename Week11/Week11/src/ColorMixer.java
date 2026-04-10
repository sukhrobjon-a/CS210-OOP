import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorMixer extends Application {

    @Override
    public void start(Stage stage) {

        double r = 0.40;
        double g = 0.45;
        double b = 0.05;

        Rectangle rect = new Rectangle(200, 200);
        Color color = new Color(r, g, b, 1.0);
        rect.setFill(color);

        Text rText = new Text(String.format("R: %.2f", r));
        Text gText = new Text(String.format("G: %.2f", g));
        Text bText = new Text(String.format("B: %.2f", b));

        String hex = String.format("#%02X%02X%02X",
                (int)(r * 255),
                (int)(g * 255),
                (int)(b * 255));

        Text hexText = new Text(hex);
        hexText.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        VBox root = new VBox(8);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new javafx.geometry.Insets(20));
        root.getChildren().addAll(rect, rText, gText, bText, hexText);

        Scene scene = new Scene(root, 300, 350);

        stage.setTitle("Color Mixer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}