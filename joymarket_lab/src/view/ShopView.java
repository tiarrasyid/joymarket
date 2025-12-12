package view;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import controllers.ShopController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Customer;
import model.Product;

public class ShopView {
    private TableView<Product> table;
    private TextField txtQuantity;
    private Button btnAdd, btnBack;

    public void start(Stage stage, Customer user) {
        Label title = new Label("Shop Products");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        // --- TABLE VIEW ---
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Product, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Product, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(value -> {
            double price = value.getValue().getProductPrice();

            String formatted = String.format("Rp. %,.0f", price);
            return new SimpleStringProperty(formatted);
        });

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("productStock"));

        table.getColumns().addAll(colName, colPrice, colStock);

        // --- INPUT SECTION ---
        Label lblQty = new Label("Quantity:");
        lblQty.setStyle("-fx-text-fill: white;");
        
        txtQuantity = new TextField();
        txtQuantity.setPromptText("Ex: 2");

        btnAdd = new Button("Add to Cart");
        btnAdd.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white;");
        
        btnBack = new Button("Back");
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6;");

        HBox inputObj = new HBox(10, lblQty, txtQuantity, btnAdd);
        inputObj.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #1e1e1e");
        layout.getChildren().addAll(title, table, inputObj, btnBack);

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Shop Page");
        stage.show();

        new ShopController(stage, this, user);
    }

    public TableView<Product> getTable() { return table; }
    public TextField getTxtQuantity() { return txtQuantity; }
    public Button getBtnAdd() { return btnAdd; }
    public Button getBtnBack() { return btnBack; }
}