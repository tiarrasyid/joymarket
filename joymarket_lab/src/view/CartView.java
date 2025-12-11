package view;

import controllers.CartController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CartItem;
import model.Customer;

public class CartView {
    private TableView<CartItem> table;
    private Label lblTotal;
    
    // Tambahan Komponen Baru (Promo & Edit)
    private TextField txtNewQty;
    private TextField txtPromo; // Field Promo Baru
    private Button btnUpdate, btnRemove; 
    private Button btnCheckout, btnBack;

    public void start(Stage stage, Customer user) {
        Label title = new Label("My Shopping Cart");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        // --- TABLE SETUP ---
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<CartItem, String> colName = new TableColumn<>("Product");
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<CartItem, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(value -> {
            double price = value.getValue().getProductPrice();

            String formatted = String.format("Rp. %,.0f", price);
            return new SimpleStringProperty(formatted);
        });

        TableColumn<CartItem, Integer> colQty = new TableColumn<>("Qty");
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<CartItem, String> colTotal = new TableColumn<>("Subtotal");
        colTotal.setCellValueFactory(value -> {
            double totalPrice = value.getValue().getTotalPrice();

            String formatted = String.format("Rp. %,.0f", totalPrice);
            return new SimpleStringProperty(formatted);
        });

        table.getColumns().addAll(colName, colPrice, colQty, colTotal);

        // --- EDIT SECTION ---
        txtNewQty = new TextField();
        txtNewQty.setPromptText("New Qty");
        txtNewQty.setPrefWidth(80);

        btnUpdate = new Button("Update Qty");
        btnUpdate.setStyle("-fx-background-color: #f59e0b; -fx-text-fill: white;"); // Orange

        btnRemove = new Button("Remove Item");
        btnRemove.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white;"); // Merah

        HBox editBox = new HBox(10, txtNewQty, btnUpdate, btnRemove);
        editBox.setAlignment(Pos.CENTER);

        // --- PROMO SECTION (BARU) ---
        Label lblPromo = new Label("Promo Code:");
        lblPromo.setStyle("-fx-text-fill: white;");
        
        txtPromo = new TextField();
        txtPromo.setPromptText("Optional");
        
        HBox promoBox = new HBox(10, lblPromo, txtPromo);
        promoBox.setAlignment(Pos.CENTER);

        // --- FOOTER SECTION ---
        lblTotal = new Label("Total: Rp 0");
        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4ade80;");

        btnCheckout = new Button("Checkout");
        btnCheckout.setPrefWidth(200);
        btnCheckout.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px;");

        btnBack = new Button("Back to Menu");
        btnBack.setPrefWidth(200);
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6; -fx-font-size: 14px;");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e");
        
        // Masukkan promoBox ke layout
        layout.getChildren().addAll(title, table, editBox, promoBox, lblTotal, btnCheckout, btnBack);

        Scene scene = new Scene(layout, 600, 600); // Tinggi ditambah dikit
        stage.setScene(scene);
        stage.setTitle("My Cart");
        stage.show();

        new CartController(stage, this, user);
    }

    public TableView<CartItem> getTable() { return table; }
    public Label getLblTotal() { return lblTotal; }
    public TextField getTxtNewQty() { return txtNewQty; }
    public TextField getTxtPromo() { return txtPromo; } // Getter Promo
    public Button getBtnUpdate() { return btnUpdate; }
    public Button getBtnRemove() { return btnRemove; }
    public Button getBtnCheckout() { return btnCheckout; }
    public Button getBtnBack() { return btnBack; }
}