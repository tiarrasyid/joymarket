package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView{
	public void start(Stage stage) {
		Label title = new Label("Main Menu");
		title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill-text:white");
		
        VBox layout = new VBox(18);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #1e1e1e;");
        
        layout.getChildren().add(title);

		Scene scene = new Scene(layout, 460, 540);
		stage.setScene(scene);
		stage.setTitle("Main menu");
		stage.show();
	}
}