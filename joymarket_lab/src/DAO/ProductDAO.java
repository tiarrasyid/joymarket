package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;

public class ProductDAO {
    private Connect db = Connect.getInstance();

    public ObservableList<Product> getAll() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        String query = "SELECT * FROM MsProduct";
        ResultSet rs = db.execQuery(query);

        try {
            while (rs != null && rs.next()) {
                products.add(new Product(
                    rs.getString("ProductID"),
                    rs.getString("ProductName"),
                    rs.getDouble("ProductPrice"),
                    rs.getInt("ProductStock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public void updateProductStock(String productId, int newStock) {
        String query = String.format("UPDATE MsProduct SET ProductStock = %d WHERE ProductID = '%s'", 
            newStock, productId);
        db.execUpdate(query);
    }
}