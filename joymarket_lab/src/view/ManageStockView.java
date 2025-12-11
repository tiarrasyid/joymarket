package view;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;

import controllers.ManageStockController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Product;
import model.User;

public class ManageStockView {
    private TableView<Product> table;
    private TextField txtStock;
    private Button btnUpdate, btnBack;

    public void start(Stage stage, User admin) {
        Label title = new Label("Manage Product Stock");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        // --- TABLE ---
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Product, String> colName = new TableColumn<>("Product Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

        TableColumn<Product, Integer> colStock = new TableColumn<>("Current Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("productStock"));

        table.getColumns().addAll(colName, colPrice, colStock);

        // --- UPDATE SECTION ---
        Label lblStock = new Label("New Stock:");
        lblStock.setStyle("-fx-text-fill: white;");

        txtStock = new TextField();
        txtStock.setPromptText("Ex: 50");
        txtStock.setPrefWidth(100);

        btnUpdate = new Button("Update Stock");
        btnUpdate.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white;");

        HBox actionBox = new HBox(10, lblStock, txtStock, btnUpdate);
        actionBox.setAlignment(Pos.CENTER);

        btnBack = new Button("Back to Admin Menu");
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6;");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #1e1e1e");
        layout.getChildren().addAll(title, table, actionBox, btnBack);

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Manage Stock");
        stage.show();

        new ManageStockController(stage, this, admin);
    }

    public TableView<Product> getTable() { return table; }
    public TextField getTxtStock() { return txtStock; }
    public Button getBtnUpdate() { return btnUpdate; }
    public Button getBtnBack() { return btnBack; }
}