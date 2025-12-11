package controllers;

import DAO.CartDAO;
import DAO.ProductDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;
import model.Product;
import view.MainMenuView;
import view.ShopView;

public class ShopController {
    private Stage stage;
    private ShopView view;
    private Customer currentUser;
    private ProductDAO pDAO = new ProductDAO();
    private CartDAO cartDAO = new CartDAO();
    private ObservableList<Product> productList;

    public ShopController(Stage stage, ShopView view, Customer currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        
        loadData();
        initAction();
    }

    private void loadData() {
        productList = pDAO.getAll();
        view.getTable().setItems(productList);
    }

    private void initAction() {
        view.getBtnAdd().setOnAction(e -> handleAddToCart());
        view.getBtnBack().setOnAction(e -> {
            MainMenuView menu = new MainMenuView();
            menu.start(stage, currentUser);
        });
    }

    private void handleAddToCart() {

        Product selectedProduct = view.getTable().getSelectionModel().getSelectedItem();
        String qtyStr = view.getTxtQuantity().getText();

        if (selectedProduct == null) {
            showAlert("Error", "Pilih produk dari tabel dulu!");
            return;
        }

        if (qtyStr.isEmpty()) {
            showAlert("Error", "Masukkan jumlah barang!");
            return;
        }

        boolean isNumeric = true;
        for (char c : qtyStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumeric = false;
                break;
            }
        }
        if (!isNumeric) {
            showAlert("Error", "Quantity harus berupa angka!");
            return;
        }

        int qty = Integer.parseInt(qtyStr);

        if (qty < 1) {
            showAlert("Error", "Minimal beli 1 barang");
            return;
        }
        if (qty > selectedProduct.getProductStock()) {
            showAlert("Error", "Stok tidak cukup! Sisa: " + selectedProduct.getProductStock());
            return;
        }

        if (cartDAO.checkItemInCart(currentUser.getUserId(), selectedProduct.getProductId())) {
            cartDAO.updateCartQty(currentUser.getUserId(), selectedProduct.getProductId(), qty);
        } else {
            cartDAO.insertToCart(currentUser.getUserId(), selectedProduct.getProductId(), qty);
        }

        showAlert("Success", "Berhasil masuk keranjang!");
        view.getTxtQuantity().clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}