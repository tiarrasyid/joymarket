package main;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ProductListView;
import view.CartCheckoutView;
import view.CustomerEditProfileView;
import view.CustomerRegisterView;
import view.CustomerTopUpView;
import view.LoginView;
import view.MainMenuView;
import view.OrderHistoryView;

public class Main extends Application{

		// TODO Auto-generated constructor stub
		public void start(Stage stage){
			CartCheckoutView test = new CartCheckoutView();
			test.start(stage);
		}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
