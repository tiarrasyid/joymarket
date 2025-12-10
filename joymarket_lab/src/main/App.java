package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;

public class App extends Application {

	public void start(Stage stage) {
		LoginView run = new LoginView();
		run.start(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
