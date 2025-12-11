package view;

import controllers.CourierStatusController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Customer;
import model.Transaction;

public class CourierStatusView {
    
    private TableView<Transaction> table;
    private Button btnBack;

    public void start(Stage stage, Customer user) {
        Label title = new Label("My Delivery Status");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        // 1. Setup TableView
        table = new TableView<>();
        table.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: black;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No active transactions"));

        // 2. Setup Kolom
        TableColumn<Transaction, String> colId = new TableColumn<>("Trx ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));

        TableColumn<Transaction, String> colInfo = new TableColumn<>("Info (Total)");
        colInfo.setCellValueFactory(new PropertyValueFactory<>("itemName")); 

        TableColumn<Transaction, String> colCourier = new TableColumn<>("Courier");
        colCourier.setCellValueFactory(new PropertyValueFactory<>("courierName"));

        TableColumn<Transaction, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(colId, colInfo, colCourier, colStatus);

        // 3. Button
        btnBack = new Button("Back to Menu");
        btnBack.setPrefWidth(200);
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6; -fx-font-size: 14px;");

        // 4. Layout
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(25));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e");
        layout.getChildren().addAll(title, table, btnBack);

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Delivery Status");
        stage.show();

        new CourierStatusController(stage, this, user);
    }

    public TableView<Transaction> getTable() { return table; }
    public Button getBtnBack() { return btnBack; }
}