import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class FlashcardApp extends Application {
    private ArrayList<String[]> cards = new ArrayList<>();
    private int currentIndex = 0;
    private boolean showingQuestion = true;

    private Label cardLabel;
    private Label indexLabel;

    @Override
    public void start(Stage stage){
        if (!loadCards()) {
            Label error = new Label("No cards found. Add cards to cards.txt and restart.");
            VBox root = new VBox(error);
            root.setAlignment(Pos.CENTER);
            Scene scene = new Scene(root, 480, 300);
            stage.setScene(scene);
            stage.setTitle("Flashcard App");
            stage.show();
            return;
        }


        indexLabel = new Label();
        indexLabel.setStyle("-fx-font-size: 14;");


        cardLabel = new Label();
        cardLabel.setWrapText(true);
        cardLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-background-color: lightblue;");
        cardLabel.setAlignment(Pos.CENTER);
        cardLabel.setMaxWidth(400);

        updateCard();


        Button flipButton = new Button("Flip");
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Previous");


        flipButton.setOnAction(e -> flipCard());
        nextButton.setOnAction(e -> nextCard());
        prevButton.setOnAction(e -> previousCard());

        VBox root = new VBox(16, indexLabel, cardLabel, flipButton, nextButton, prevButton);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 480, 300);
        stage.setTitle("Flashcard App");
        stage.setScene(scene);
        stage.show();
    }

    private boolean loadCards(){
        File file = new File("cards.txt");
        if (!file.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;

            while ((line = reader.readLine()) !=null){
                String[] parts = line.split("\\|");
                if (parts.length ==2){
                    cards.add(parts);
                }
            }
        } catch (IOException E){
            return false;
        }
        return !cards.isEmpty();
    }

    private void updateCard() {
        String[] card = cards.get(currentIndex);

        indexLabel.setText("Card " + (currentIndex + 1) + " / " + cards.size());

        if (showingQuestion) {
            cardLabel.setText(card[0]);
            cardLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-background-color: lightblue;");
        } else {
            cardLabel.setText(card[1]);
            cardLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-background-color: lightgreen;");
        }
    }

    private void flipCard() {
        showingQuestion = !showingQuestion;
        updateCard();
    }

    private void nextCard() {
        currentIndex = (currentIndex + 1) % cards.size();
        showingQuestion = true;
        updateCard();
    }

    private void previousCard() {
        currentIndex = (currentIndex - 1 + cards.size()) % cards.size();
        showingQuestion = true;
        updateCard();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
