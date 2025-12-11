package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import database.Connect;

public class PromoDAO {
    private Connect db = Connect.getInstance();

    public double getDiscount(String code) {
        String query = String.format("SELECT DiscountAmount FROM MsPromo WHERE PromoCode = '%s'", code);
        ResultSet rs = db.execQuery(query);
        try {
            if (rs != null && rs.next()) {
                return rs.getDouble("DiscountAmount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 kalau tidak ditemukan
    }
}