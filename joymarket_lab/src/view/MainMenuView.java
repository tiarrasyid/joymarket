package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Customer;
import model.User;

public class MainMenuView {
    
    public void start(Stage stage, User user) {
        Customer customer = (Customer) user; 

        Label title = new Label("Main Menu");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white");

        /* ===== USER INFO ===== */
        Label lblName = new Label("Name : " + customer.getUserName());
        Label lblEmail = new Label("Email : " + customer.getUserEmail());
        Label lblRole = new Label("Role : " + customer.getUserRole());
        Label lblBalance = new Label("Balance : Rp " + String.format("%.0f", customer.getUserBalance()));

        lblName.setStyle("-fx-text-fill: white;");
        lblEmail.setStyle("-fx-text-fill: white;");
        lblRole.setStyle("-fx-text-fill: white;");
        lblBalance.setStyle("-fx-text-fill: #4ade80; -fx-font-weight: bold;");

        VBox userInfoBox = new VBox(8);
        userInfoBox.getChildren().addAll(lblName, lblEmail, lblRole, lblBalance);
        userInfoBox.setPadding(new Insets(15));
        userInfoBox.setAlignment(Pos.CENTER_LEFT);
        userInfoBox.setMaxWidth(350);
        userInfoBox.setStyle("-fx-background-color: #2a2a2a; -fx-background-radius: 10;");

        /* ===== BUTTONS ===== */
        
        // 1. Edit Profile (BARU)
        Button btnProfile = new Button("Edit Profile");
        btnProfile.setPrefWidth(200);
        btnProfile.setStyle("-fx-background-color: #8b5cf6; -fx-text-fill: white; -fx-font-size: 14px;");
        btnProfile.setOnAction(e -> {
            EditProfileView profileView = new EditProfileView();
            profileView.start(stage, customer);
        });

        // 2. Top Up
        Button btnTopUp = new Button("Top Up Balance");
        btnTopUp.setPrefWidth(200);
        btnTopUp.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px;");
        btnTopUp.setOnAction(e -> {
            CustomerTopUpView topUpView = new CustomerTopUpView();
            topUpView.start(stage, customer); 
        });

        // 3. Shop
        Button btnShop = new Button("Buy Products");
        btnShop.setPrefWidth(200);
        btnShop.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px;");
        btnShop.setOnAction(e -> {
            ShopView shopView = new ShopView();
            shopView.start(stage, customer);
        });
        
        // 4. Cart
        Button btnCart = new Button("My Cart");
        btnCart.setPrefWidth(200);
        btnCart.setStyle("-fx-background-color: #f59e0b; -fx-text-fill: white; -fx-font-size: 14px;");
        btnCart.setOnAction(e -> {
            CartView cartView = new CartView();
            cartView.start(stage, customer);
        });

        // 5. Courier Status
        Button btnStatus = new Button("Check Delivery Status");
        btnStatus.setPrefWidth(200);
        btnStatus.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px;");
        btnStatus.setOnAction(e -> {
            CourierStatusView statusView = new CourierStatusView();
            statusView.start(stage, customer);
        });

        // 6. Logout
        Button btnLogout = new Button("Logout");
        btnLogout.setPrefWidth(200);
        btnLogout.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-size: 14px;");
        btnLogout.setOnAction(e -> {
            LoginView login = new LoginView();
            login.start(stage);
        });

        /* ===== MAIN LAYOUT ===== */
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #1e1e1e;");
        
        // UPDATE DISINI: Masukkan btnProfile ke dalam daftar anak-anak layout
        layout.getChildren().addAll(title, userInfoBox, btnProfile, btnTopUp, btnShop, btnCart, btnStatus, btnLogout);

        Scene scene = new Scene(layout, 460, 650); // Tinggi ditambah dikit biar muat
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }
}