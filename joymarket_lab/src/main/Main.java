package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        LoginView login = new LoginView();
        login.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}