package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AdminMenuView {
    
    public void start(Stage stage, User admin) {
        Label title = new Label("Admin Dashboard");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white");

        Label lblWelcome = new Label("Welcome, " + admin.getUserName());
        lblWelcome.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        // TOMBOL 1: Assign Courier
        Button btnAssign = new Button("Assign Orders to Courier");
        btnAssign.setPrefWidth(220);
        btnAssign.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px;");
        btnAssign.setOnAction(e -> {
            AssignCourierView assignView = new AssignCourierView();
            assignView.start(stage, admin);
        });

        // TOMBOL 2: Manage Stock
        Button btnStock = new Button("Manage Product Stock");
        btnStock.setPrefWidth(220);
        btnStock.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px;");
        
        // AKTIFKAN KE VIEW BARU
        btnStock.setOnAction(e -> {
            ManageStockView stockView = new ManageStockView();
            stockView.start(stage, admin);
        });
        
        // TOMBOL 3: Logout
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
        layout.getChildren().addAll(title, lblWelcome, btnAssign, btnStock, btnLogout);

        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Admin Menu");
        stage.show();
    }
}