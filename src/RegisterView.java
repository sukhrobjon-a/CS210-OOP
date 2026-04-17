import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {

    private Stage stage;

    public RegisterView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        stage.setTitle("Register");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        Label messageLabel = new Label();

        Button registerButton = new Button("Register");

        registerButton.setOnAction(e -> {

            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (username.isEmpty() || email.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty()) {

                messageLabel.setText("All fields must be filled.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                messageLabel.setText("Passwords do not match.");
                return;
            }

            User newUser = new User(username, password, email);

            if (UserService.registerUser(newUser)) {
                messageLabel.setText("Registration successful!");

                LoginView loginView = new LoginView(stage);
                loginView.show();
            } else {
                messageLabel.setText("Username or email already exists.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("Username"), usernameField,
                new Label("Email"), emailField,
                new Label("Password"), passwordField,
                new Label("Confirm Password"), confirmPasswordField,
                registerButton,
                messageLabel
        );

        Scene scene = new Scene(layout, 350, 400);
        stage.setScene(scene);
        stage.show();
    }
}