package view;

import controllers.CourierController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Transaction;
import model.User;

public class CourierOrderView {
    private TableView<Transaction> table;
    private ComboBox<String> cbStatus;
    private Button btnUpdate, btnBack;

    public void start(Stage stage, User courier) {
        Label title = new Label("My Delivery Tasks");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Transaction, String> colID = new TableColumn<>("Trx ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("transactionId"));

        TableColumn<Transaction, String> colInfo = new TableColumn<>("Customer Info");
        colInfo.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Transaction, String> colStatus = new TableColumn<>("Current Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(colID, colInfo, colStatus);

        Label lblStatus = new Label("Update Status:");
        lblStatus.setStyle("-fx-text-fill: white;");

        cbStatus = new ComboBox<>();

        cbStatus.getItems().addAll("Pending", "In Progress", "Delivered");
        cbStatus.setPromptText("Select new status...");
        cbStatus.setPrefWidth(180);

        btnUpdate = new Button("Update");
        btnUpdate.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white;");

        HBox actionBox = new HBox(10, lblStatus, cbStatus, btnUpdate);
        actionBox.setAlignment(Pos.CENTER);

        btnBack = new Button("Back");
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6;");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #1e1e1e");
        layout.getChildren().addAll(title, table, actionBox, btnBack);

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Manage Delivery");
        stage.show();

        new CourierController(stage, this, courier);
    }

    public TableView<Transaction> getTable() { return table; }
    public ComboBox<String> getCbStatus() { return cbStatus; }
    public Button getBtnUpdate() { return btnUpdate; }
    public Button getBtnBack() { return btnBack; }
}