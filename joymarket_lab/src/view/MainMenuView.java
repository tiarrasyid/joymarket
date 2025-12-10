package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class MainMenuView{
	public void start(Stage stage, User user) {
    Label title = new Label("Main Menu");
    title.setStyle(
        "-fx-font-size: 20px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: white;"
    );

    /* ===== USER INFO ===== */
    Label lblName = new Label("Name : " + user.getUserName());
    Label lblEmail = new Label("Email : " + user.getUserEmail());
    Label lblRole = new Label("Role : " + user.getUserRole());

    lblName.setStyle("-fx-text-fill: white;");
    lblEmail.setStyle("-fx-text-fill: white;");
    lblRole.setStyle("-fx-text-fill: white;");

    VBox userInfoBox = new VBox(8);
    userInfoBox.getChildren().addAll(lblName, lblEmail, lblRole);
    userInfoBox.setPadding(new Insets(15));
    userInfoBox.setAlignment(Pos.CENTER_LEFT);
    userInfoBox.setMaxWidth(350);
    userInfoBox.setStyle(
        "-fx-background-color: #2a2a2a;" +
        "-fx-background-radius: 10;"
    );

    /* ===== MAIN LAYOUT ===== */
    VBox layout = new VBox(18);
    layout.setAlignment(Pos.CENTER);
    layout.setPadding(new Insets(25));
    layout.setStyle("-fx-background-color: #1e1e1e;");
    layout.getChildren().addAll(title, userInfoBox);

    Scene scene = new Scene(layout, 460, 540);
    stage.setScene(scene);
    stage.setTitle("Main Menu");
    stage.show();
}

}