import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimer extends Application {

    private int remainingSeconds = 0;
    private Timeline timeline;
    private FadeTransition fade;

    private Label timeLabel;
    private Label statusLabel;
    private TextField inputField;

    @Override
    public void start(Stage stage) {

        inputField = new TextField();
        inputField.setPromptText("Enter minutes");

        Button startButton = new Button("Start");
        Button pauseButton = new Button("Pause / Resume");
        Button resetButton = new Button("Reset");

        statusLabel = new Label();

        timeLabel = new Label("00:00");
        timeLabel.setFont(Font.font("monospace", FontWeight.BOLD, 52));
        timeLabel.setStyle("-fx-text-fill: black;");



        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateTimer())
        );
        timeline.setCycleCount(Animation.INDEFINITE);



        fade = new FadeTransition(Duration.seconds(0.5), timeLabel);
        fade.setFromValue(1.0);
        fade.setToValue(0.1);
        fade.setAutoReverse(true);
        fade.setCycleCount(Animation.INDEFINITE);


        startButton.setOnAction(e -> startTimer());
        pauseButton.setOnAction(e -> pauseResume());
        resetButton.setOnAction(e -> resetTimer());

        VBox root = new VBox(16,
                inputField,
                startButton,
                pauseButton,
                resetButton,
                statusLabel,
                timeLabel
        );


        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 340, 260);
        stage.setTitle("Countdown Timer");
        stage.setScene(scene);
        stage.show();
    }

    private void startTimer() {
        String text = inputField.getText().trim();

        int minutes;

        try {
            minutes = Integer.parseInt(text);
            if (minutes <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText("Enter a valid number > 0");
            return;
        }

        remainingSeconds = minutes * 60;

        fade.stop();
        timeLabel.setOpacity(1.0);
        timeLabel.setStyle("-fx-text-fill: black;");

        updateLabel();

        timeline.stop();
        timeline.play();

        statusLabel.setText("");
    }

    private void updateTimer() {
        if (remainingSeconds > 0) {
            remainingSeconds--;
            updateLabel();
        } else {
            timeline.stop();
            timeUp();
        }
    }

    private void updateLabel() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;

        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void pauseResume() {
        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
        } else if (timeline.getStatus() == Animation.Status.PAUSED) {
            timeline.play();
        }
    }

    private void resetTimer() {
        timeline.stop();
        fade.stop();

        remainingSeconds = 0;
        timeLabel.setText("00:00");
        timeLabel.setOpacity(1.0);
        timeLabel.setStyle("-fx-text-fill: black;");

        statusLabel.setText("");
    }

    private void timeUp() {
        timeLabel.setStyle("-fx-text-fill: red;");
        fade.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}