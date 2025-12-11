package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import src.view.CourierOrderView;
import src.view.LoginView;

public class CourierMenuView {
    
    public void start(Stage stage, User courier) {
        Label title = new Label("Courier Dashboard");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white");

        Label lblWelcome = new Label("Welcome, " + courier.getUserName());
        lblWelcome.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        // TOMBOL 1: Lihat Pesanan (Orders)
        Button btnOrders = new Button("My Delivery Orders");
        btnOrders.setPrefWidth(220);
        btnOrders.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px;");
        btnOrders.setOnAction(e -> {
            CourierOrderView orderView = new CourierOrderView();
            orderView.start(stage, courier);
        });
        
        // TOMBOL 2: Logout
        Button btnLogout = new Button("Logout");
        btnLogout.setPrefWidth(220);
        btnLogout.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-size: 14px;");
        btnLogout.setOnAction(e -> {
            LoginView login = new LoginView();
            login.start(stage); 
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #1e1e1e;");
        layout.getChildren().addAll(title, lblWelcome, btnOrders, btnLogout);

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Courier Menu");
        stage.show();
    }
}