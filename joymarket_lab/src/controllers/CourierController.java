package controllers;

import DAO.TransactionDAO;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Transaction;
import model.User;
import view.CourierMenuView;
import view.CourierOrderView;

public class CourierController {
    private Stage stage;
    private CourierOrderView view;
    private User currentUser;
    private TransactionDAO trxDAO = new TransactionDAO();

    public CourierController(Stage stage, CourierOrderView view, User currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        
        loadData();
        initAction();
    }

    private void loadData() {
        view.getTable().setItems(trxDAO.getCourierTransactions(currentUser.getUserId()));
    }

    private void initAction() {
        view.getBtnUpdate().setOnAction(e -> handleUpdate());
        view.getBtnBack().setOnAction(e -> {
            CourierMenuView menu = new CourierMenuView();
            menu.start(stage, currentUser);
        });
    }

    private void handleUpdate() {
        Transaction selectedTrx = view.getTable().getSelectionModel().getSelectedItem();
        if (selectedTrx == null) {
            showAlert("Error", "Pilih transaksi yang mau diupdate!");
            return;
        }

        String newStatus = view.getCbStatus().getValue();
        if (newStatus == null) {
            showAlert("Error", "Pilih status baru dulu!");
            return;
        }

        trxDAO.updateStatus(selectedTrx.getTransactionId(), newStatus);
        
        showAlert("Success", "Status berhasil diubah menjadi: " + newStatus);
        
        loadData();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}