package controllers;

import DAO.ProductDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Product;
import model.User;
import view.AdminMenuView;
import view.ManageStockView;

public class ManageStockController {
    private Stage stage;
    private ManageStockView view;
    private User currentUser;
    private ProductDAO pDAO = new ProductDAO();

    public ManageStockController(Stage stage, ManageStockView view, User currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        
        loadData();
        initAction();
    }

    private void loadData() {
        view.getTable().setItems(pDAO.getAll());
    }

    private void initAction() {
        view.getBtnUpdate().setOnAction(e -> handleUpdate());
        view.getBtnBack().setOnAction(e -> {
            AdminMenuView menu = new AdminMenuView();
            menu.start(stage, currentUser);
        });
        
        view.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                view.getTxtStock().setText(String.valueOf(newVal.getProductStock()));
            }
        });
    }

    private void handleUpdate() {
        Product selectedProduct = view.getTable().getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert("Error", "Pilih produk dari tabel dulu!");
            return;
        }

        String stockStr = view.getTxtStock().getText();

        if (stockStr.isEmpty()) {
            showAlert("Error", "Masukkan jumlah stok!");
            return;
        }

        boolean isNumeric = true;
        for (char c : stockStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumeric = false;
                break;
            }
        }
        if (!isNumeric) {
            showAlert("Error", "Stok harus berupa angka!");
            return;
        }

        int newStock = Integer.parseInt(stockStr);

        if (newStock < 0) {
            showAlert("Error", "Stok tidak boleh negatif!");
            return;
        }

        pDAO.updateProductStock(selectedProduct.getProductId(), newStock);
        
        showAlert("Success", "Stok berhasil diupdate!");
        view.getTxtStock().clear();
        
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