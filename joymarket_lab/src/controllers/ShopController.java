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
        // 1. Ambil Produk yang dipilih di Tabel
        Product selectedProduct = view.getTable().getSelectionModel().getSelectedItem();
        String qtyStr = view.getTxtQuantity().getText();

        // VALIDASI 1: Harus pilih produk
        if (selectedProduct == null) {
            showAlert("Error", "Pilih produk dari tabel dulu!");
            return;
        }

        // VALIDASI 2: Quantity tidak boleh kosong
        if (qtyStr.isEmpty()) {
            showAlert("Error", "Masukkan jumlah barang!");
            return;
        }

        // VALIDASI 3: Quantity harus angka (MANUAL - TANPA REGEX)
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

        // VALIDASI 4: Minimal 1 dan Maksimal sesuai Stock (Sesuai Soal)
        if (qty < 1) {
            showAlert("Error", "Minimal beli 1 barang");
            return;
        }
        if (qty > selectedProduct.getProductStock()) {
            showAlert("Error", "Stok tidak cukup! Sisa: " + selectedProduct.getProductStock());
            return;
        }

        // --- PROSES SAVE KE DATABASE ---
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