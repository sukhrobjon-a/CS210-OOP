import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeView {

    private Stage stage;
    private String username;

    public WelcomeView(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
    }

    public void show() {

        stage.setTitle("Welcome");

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        Button logoutButton = new Button("Log Out");

        logoutButton.setOnAction(e -> {
            LoginView loginView = new LoginView(stage);
            loginView.show();
        });

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(welcomeLabel, logoutButton);

        Scene scene = new Scene(layout, 600, 600);
        stage.setScene(scene);
        stage.show();
    }
}