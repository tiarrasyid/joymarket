package view;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;

import controllers.AssignCourierController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Customer;
import model.Transaction;
import model.User;

public class AssignCourierView {
    private TableView<Transaction> table;
    private ComboBox<String> cbCouriers;
    private Button btnAssign, btnBack;

    public void start(Stage stage, User admin) {
        Label title = new Label("Assign Order to Courier");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        // TABLE
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Transaction, String> colID = new TableColumn<>("Trx ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("transactionId"));

        TableColumn<Transaction, String> colInfo = new TableColumn<>("Info");
        colInfo.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Transaction, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(colID, colInfo, colStatus);

        // INPUT SECTION
        Label lblCourier = new Label("Select Courier:");
        lblCourier.setStyle("-fx-text-fill: white;");

        cbCouriers = new ComboBox<>();
        cbCouriers.setPromptText("Choose Courier...");
        cbCouriers.setPrefWidth(200);

        btnAssign = new Button("Assign");
        btnAssign.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white;");

        HBox actionBox = new HBox(10, lblCourier, cbCouriers, btnAssign);
        actionBox.setAlignment(Pos.CENTER);

        btnBack = new Button("Back");
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6;");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #1e1e1e");
        layout.getChildren().addAll(title, table, actionBox, btnBack);

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Assign Courier");
        stage.show();

        new AssignCourierController(stage, this, admin);
    }

    public TableView<Transaction> getTable() { return table; }
    public ComboBox<String> getCbCouriers() { return cbCouriers; }
    public Button getBtnAssign() { return btnAssign; }
    public Button getBtnBack() { return btnBack; }
}