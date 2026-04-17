import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private Stage stage;

    public LoginView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("Login");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label messageLabel = new Label();

        Button loginButton = new Button("Log In");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (UserService.validateLogin(username, password)) {
                WelcomeView welcomeView = new WelcomeView(stage, username);
                welcomeView.show();
            } else {
                messageLabel.setText("Incorrect username or password.");
            }
        });

        registerButton.setOnAction(e -> {
            RegisterView registerView = new RegisterView(stage);
            registerView.show();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("Username"), usernameField,
                new Label("Password"), passwordField,
                loginButton,
                registerButton,
                messageLabel
        );

        Scene scene = new Scene(layout, 600, 600);
        stage.setScene(scene);
        stage.show();
    }
}