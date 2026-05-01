package com.carrental;

import com.carrental.ui.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        new LoginView(stage).show();
    }

    public static void main(String[] args) {
        launch();
    }
}