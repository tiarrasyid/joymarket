package controllers;

import java.time.LocalDate;
import DAO.CartDAO;
import DAO.ProductDAO;
import DAO.PromoDAO; // Import PromoDAO
import DAO.TransactionDAO;
import database.Connect;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.CartItem;
import model.Customer;
import model.Product;
import view.CartView;
import view.MainMenuView;

public class CartController {
    private Stage stage;
    private CartView view;
    private Customer currentUser;
    
    private CartDAO cartDAO = new CartDAO();
    private TransactionDAO trxDAO = new TransactionDAO();
    private ProductDAO pDAO = new ProductDAO();
    private PromoDAO promoDAO = new PromoDAO();
    private Connect db = Connect.getInstance(); 
    
    private ObservableList<CartItem> cartItems;
    private double grandTotal = 0.0;

    public CartController(Stage stage, CartView view, Customer currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        
        loadCart();
        initAction();
    }

    private void loadCart() {
        cartItems = cartDAO.getUserCart(currentUser.getUserId());
        view.getTable().setItems(cartItems);
        
        grandTotal = 0;
        for (CartItem item : cartItems) {
            grandTotal += item.getTotalPrice();
        }
        view.getLblTotal().setText("Total: Rp " + String.format("%,.0f", grandTotal));
    }

    private void initAction() {
        view.getBtnCheckout().setOnAction(e -> handleCheckout());
        view.getBtnUpdate().setOnAction(e -> handleUpdate());
        view.getBtnRemove().setOnAction(e -> handleRemove());
        
        view.getBtnBack().setOnAction(e -> {
            MainMenuView menu = new MainMenuView();
            menu.start(stage, currentUser);
        });
        
        view.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                view.getTxtNewQty().setText(String.valueOf(newVal.getQuantity()));
            }
        });
    }

    private void handleRemove() {
        CartItem selected = view.getTable().getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Pilih barang yang mau dihapus!");
            return;
        }

        cartDAO.deleteItem(currentUser.getUserId(), selected.getProductId());
        
        showAlert("Success", "Barang dihapus dari keranjang.");
        loadCart();
    }

    private void handleUpdate() {
        CartItem selected = view.getTable().getSelectionModel().getSelectedItem();
        String qtyStr = view.getTxtNewQty().getText();

        if (selected == null) {
            showAlert("Error", "Pilih barang dulu!");
            return;
        }
        if (qtyStr.isEmpty()) {
            showAlert("Error", "Masukkan jumlah baru!");
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
            showAlert("Error", "Quantity harus angka!");
            return;
        }

        int newQty = Integer.parseInt(qtyStr);
        if (newQty < 1) {
            showAlert("Error", "Minimal 1 barang.");
            return;
        }

        int currentStock = 0;
        for(Product p : pDAO.getAll()) {
            if(p.getProductId().equals(selected.getProductId())) {
                currentStock = p.getProductStock();
                break;
            }
        }

        if (newQty > currentStock) {
            showAlert("Error", "Stok tidak cukup! Sisa: " + currentStock);
            return;
        }

        cartDAO.updateExactQty(currentUser.getUserId(), selected.getProductId(), newQty);
        
        showAlert("Success", "Jumlah barang berhasil diubah.");
        view.getTxtNewQty().clear();
        loadCart();
    }

    private void handleCheckout() {
        if (cartItems.isEmpty()) {
            showAlert("Error", "Keranjang kosong! Belanja dulu.");
            return;
        }

        // --- LOGIC PROMO CODE ---
        String code = view.getTxtPromo().getText().trim();
        double discount = 0;
        
        if (!code.isEmpty()) {
            discount = promoDAO.getDiscount(code);
            // Sesuai soal: If a promo code is entered, it must exist.
            if (discount == 0) {
                showAlert("Error", "Promo Code tidak valid!");
                return;
            }
        }

        // Hitung Total Akhir setelah diskon
        double finalTotal = grandTotal - discount;
        if (finalTotal < 0) finalTotal = 0; // Tidak boleh minus

        // Validasi Saldo dengan Final Total
        if (currentUser.getUserBalance() < finalTotal) {
            showAlert("Failed", "Saldo tidak cukup! Total Bayar: Rp " + finalTotal);
            return;
        }

        // --- PROSES INSERT DB ---
        String trxId = trxDAO.generateTrxId();
        String date = LocalDate.now().toString();

        // Simpan Transaksi dengan harga Final
        if (trxDAO.insertHeader(trxId, currentUser.getUserId(), date, finalTotal)) {
            for (CartItem item : cartItems) {
                trxDAO.insertDetail(trxId, item.getProductId(), item.getQuantity());
                
                String updateStockQuery = String.format("UPDATE MsProduct SET ProductStock = ProductStock - %d WHERE ProductID = '%s'", 
                        item.getQuantity(), item.getProductId());
                db.execUpdate(updateStockQuery);
            }

            // Kurangi Saldo User dengan harga Final
            String updateBalanceQuery = String.format(java.util.Locale.US, 
                    "UPDATE MsUser SET UserBalance = UserBalance - %f WHERE UserID = '%s'", 
                    finalTotal, currentUser.getUserId());
            db.execUpdate(updateBalanceQuery);

            // Update Saldo di Memory
            currentUser.setUserBalance(currentUser.getUserBalance() - finalTotal);
            
            // Bersihkan Cart
            cartDAO.clearCart(currentUser.getUserId());

            String msg = "Checkout Berhasil!";
            if (discount > 0) {
                msg += "\nAnda hemat: Rp " + discount;
            }
            showAlert("Success", msg);
            
            MainMenuView menu = new MainMenuView();
            menu.start(stage, currentUser);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}