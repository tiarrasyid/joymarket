package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenuView {

    private Button btnTopUp;
    private Button btnBrowseProduct;
    private Button btnCart;
    private Button btnOrderHistory;
    private Button btnEditProfile;
    private Button btnLogout;

    public void start(Stage stage) {

        Label title = new Label("Main Menu");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox.setMargin(title, new Insets(0, 0, 20, 0));

        btnBrowseProduct = createCardBtn("Browse\nProducts");
        btnCart = createCardBtn("Cart &\nCheckout");
        btnOrderHistory = createCardBtn("Order\nHistory");

        btnTopUp = createCardBtn("Top Up\nBalance");
        btnEditProfile = createCardBtn("Edit\nProfile");

        // first row: 3 card
        GridPane grid = new GridPane();
        grid.setHgap(18);
        grid.setVgap(25);
        grid.setAlignment(Pos.CENTER);

        grid.add(btnBrowseProduct, 0, 0);
        grid.add(btnCart, 1, 0);
        grid.add(btnOrderHistory, 2, 0);


        // sec row 
        HBox secondRow = new HBox(20);
        secondRow.setAlignment(Pos.CENTER);
        secondRow.getChildren().addAll(btnTopUp, btnEditProfile);

        grid.add(secondRow, 0, 1, 3, 1); 


        btnLogout = new Button("Logout");
        btnLogout.setStyle(
            "-fx-background-color: #ef4444;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 10 20;" +
            "-fx-background-radius: 8;"
        );
        btnLogout.setPrefWidth(260);

        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #1e1e1e;");

        layout.getChildren().addAll(title, grid, btnLogout);

        Scene scene = new Scene(layout, 640, 520);
        stage.setScene(scene);
        stage.setTitle("Customer Menu");
        stage.show();
    }


    private Button createCardBtn(String text) {
        Button btn = new Button(text);
        btn.setStyle(
            "-fx-background-color: #3b82f6;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 25;" +
            "-fx-background-radius: 12;" +
            "-fx-alignment: center;"
        );
        btn.setPrefSize(160, 100);
        return btn;
    }

    public Button getBtnTopUp() { return btnTopUp; }
    public Button getBtnBrowseProduct() { return btnBrowseProduct; }
    public Button getBtnCart() { return btnCart; }
    public Button getBtnOrderHistory() { return btnOrderHistory; }
    public Button getBtnEditProfile() { return btnEditProfile; }
    public Button getBtnLogout() { return btnLogout; }
}
