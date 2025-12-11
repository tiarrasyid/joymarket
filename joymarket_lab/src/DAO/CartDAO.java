package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CartItem;

public class CartDAO {
    private Connect db = Connect.getInstance();

    // 1. Ambil data keranjang lengkap dengan Nama Produk & Harga (JOIN Table)
    public ObservableList<CartItem> getUserCart(String userId) {
        ObservableList<CartItem> cartItems = FXCollections.observableArrayList();
        
        // Query JOIN antara MsCart dan MsProduct
        String query = String.format("SELECT c.ProductID, p.ProductName, p.ProductPrice, c.Quantity " +
                                     "FROM MsCart c " +
                                     "JOIN MsProduct p ON c.ProductID = p.ProductID " +
                                     "WHERE c.UserID = '%s'", userId);
        
        ResultSet rs = db.execQuery(query);
        try {
            while (rs != null && rs.next()) {
                cartItems.add(new CartItem(
                    rs.getString("ProductID"),
                    rs.getString("ProductName"),
                    rs.getDouble("ProductPrice"),
                    rs.getInt("Quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // 2. Cek apakah item sudah ada di cart user (agar tidak double row)
    public boolean checkItemInCart(String userId, String productId) {
        String query = String.format("SELECT * FROM MsCart WHERE UserID = '%s' AND ProductID = '%s'", userId, productId);
        ResultSet rs = db.execQuery(query);
        try {
            return rs != null && rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Masukkan barang baru ke cart
    public void insertToCart(String userId, String productId, int qty) {
        String query = String.format(Locale.US, "INSERT INTO MsCart VALUES ('%s', '%s', %d)", userId, productId, qty);
        db.execUpdate(query);
    }

    // 4. Update quantity jika barang sudah ada (ditambah qty baru)
    public void updateCartQty(String userId, String productId, int extraQty) {
        String query = String.format("UPDATE MsCart SET Quantity = Quantity + %d WHERE UserID = '%s' AND ProductID = '%s'", extraQty, userId, productId);
        db.execUpdate(query);
    }

    // 5. Hapus semua isi keranjang user (dipanggil setelah Checkout berhasil)
    public void clearCart(String userId) {
        String query = String.format("DELETE FROM MsCart WHERE UserID = '%s'", userId);
        db.execUpdate(query);
    }
    
    // HAPUS SATU BARANG DARI KERANJANG (Fitur Delete)
    public void deleteItem(String userId, String productId) {
        String query = String.format("DELETE FROM MsCart WHERE UserID = '%s' AND ProductID = '%s'", userId, productId);
        db.execUpdate(query);
    }

    // UPDATE JUMLAH BARANG DI KERANJANG (Fitur Update Cart)
    public void updateExactQty(String userId, String productId, int newQty) {
        String query = String.format("UPDATE MsCart SET Quantity = %d WHERE UserID = '%s' AND ProductID = '%s'", 
            newQty, userId, productId);
        db.execUpdate(query);
    }
}