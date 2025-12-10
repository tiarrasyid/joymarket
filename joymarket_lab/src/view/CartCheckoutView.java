package view;

import java.util.HashMap;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CartCheckoutView {

    private class CartItem {
        String name;
        int price;
        int qty;

        CartItem(String name, int price, int qty) {
            this.name = name;
            this.price = price;
            this.qty = qty;
        }

        int getTotal() { return price * qty; }
    }

    private HashMap<String, CartItem> cart = new HashMap<>();
    private HashMap<String, Integer> promoDB = new HashMap<>();

    private int customerBalance = 100000;

    private GridPane cartListContainer;

    private TextField tfPromo;
    private Button btnApplyPromo;
    private Label lblPromoStatus;

    private Label lblTotal;
    private Button btnCheckout;
    private Button btnBack;

    private int activeDiscount = 0; 

    private final String fieldStyle = "-fx-text-fill: white; -fx-control-inner-background: #2b2b2b";
    private final int fieldWidth = 220;

    public CartCheckoutView() {
        cart.put("Book A", new CartItem("Product 1", 432434, 1));
        cart.put("Book B", new CartItem("Product 2", 324343, 2));

        promoDB.put("DISKON10", 10);
        promoDB.put("HEMAT20", 20);
    }

    public void start(Stage stage) {

        Label title = new Label("Your Cart");
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20px");
        GridPane.setHalignment(title, HPos.CENTER);

        cartListContainer = new GridPane();
        cartListContainer.setVgap(10);
        cartListContainer.setHgap(10);
        refreshCartList();

        tfPromo = createTextField("Enter promo code");

        btnApplyPromo = new Button("Apply");
        btnApplyPromo.setPrefWidth(120);
        btnApplyPromo.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding:10 20");
//        btnApplyPromo.setOnAction(e -> applyPromo());

        lblPromoStatus = new Label();
        lblPromoStatus.setStyle("-fx-text-fill: #ef4444;");

        lblTotal = new Label();
        lblTotal.setStyle("-fx-text-fill: white; -fx-font-size: 14px");
//        updateTotal();

        btnCheckout = new Button("Checkout");
        btnCheckout.setPrefWidth(200);
        btnCheckout.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding:10 20");
//        btnCheckout.setOnAction(e -> handleCheckout());

        btnBack = new Button("Back to Menu");
        btnBack.setPrefWidth(200);
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6; -fx-background-radius: 8; -fx-padding:10 20");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(25));
        layout.setVgap(12);
        layout.setHgap(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e;");

        int row = 0;
        
        layout.add(title, 0, row++, 2, 1);

        layout.add(cartListContainer, 0, row++, 2, 1);

        layout.add(createLabel("Promo Code:"), 0, row);
        layout.add(tfPromo, 1, row++);

        layout.add(btnApplyPromo, 1, row++);

        layout.add(lblPromoStatus, 1, row++);

        layout.add(lblTotal, 1, row++);

        layout.add(btnCheckout, 1, row++);
        layout.add(btnBack, 1, row++);

        Scene scene = new Scene(layout, 550, 500);
        stage.setScene(scene);
        stage.setTitle("Cart");
        stage.show();
    }

    private void refreshCartList() {
        cartListContainer.getChildren().clear();

        int r = 0;
        for (CartItem item : cart.values()) {

            Label lblName = createLabel(item.name + " (Rp " + item.price + ")");
            Spinner<Integer> qtySpinner = new Spinner<>(1, 20, item.qty);
            qtySpinner.valueProperty().addListener((obs, oldV, newV) -> {
                item.qty = newV;
            });

            Button btnRemove = new Button("Remove");
            btnRemove.setPrefWidth(90);
            btnRemove.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 6;");
            btnRemove.setOnAction(e -> {
                cart.remove(item.name);
                refreshCartList();

            });

            cartListContainer.add(lblName, 0, r);
            cartListContainer.add(qtySpinner, 1, r);
            cartListContainer.add(btnRemove, 2, r);

            r++;
        }
    }


    private TextField createTextField(String placeholder) {
        TextField tf = new TextField();
        tf.setPromptText(placeholder);
        tf.setStyle(fieldStyle);
        tf.setPrefWidth(fieldWidth);
        return tf;
    }

    private Label createLabel(String text) {
        Label lbl = new Label(text);
        lbl.setStyle("-fx-text-fill: white");
        return lbl;
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public Button getBtnCheckout() { return btnCheckout; }
    public Button getBtnBack() { return btnBack; }
    public Button getBtnApplyPromo() { return btnApplyPromo; }
}
