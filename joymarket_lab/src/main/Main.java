package main;
import javafx.application.Application;
import javafx.stage.Stage;
import view.CustomerRegisterView;
import view.CustomerTopUpView;
import view.LoginView;
import view.MainMenuView;

public class Main extends Application{

		// TODO Auto-generated constructor stub
		public void start(Stage stage){
			CustomerTopUpView test = new CustomerTopUpView();
			test.start(stage);
		}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
