package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Transaction;

public class TransactionDAO {
    private Connect db = Connect.getInstance();

    public boolean insertHeader(String trxId, String userId, String date, double total) {
        String query = String.format(Locale.US, 
            "INSERT INTO MsTransaction (TransactionID, UserID, TransactionDate, TotalPrice, Status) " +
            "VALUES ('%s', '%s', '%s', %f, 'Pending')",
            trxId, userId, date, total);
        return db.execUpdate(query);
    }

    public void insertDetail(String trxId, String prodId, int qty) {
        String query = String.format(Locale.US, 
            "INSERT INTO MsTransactionDetail VALUES ('%s', '%s', %d)",
            trxId, prodId, qty);
        db.execUpdate(query);
    }
    
    public ObservableList<Transaction> getUserTransactions(String userId) {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        String query = String.format(Locale.US, "SELECT * FROM MsTransaction WHERE UserID = '%s'", userId);
        ResultSet rs = db.execQuery(query);
        try {
            while (rs != null && rs.next()) {
                String info = "Total: Rp " + String.format("%.0f", rs.getDouble("TotalPrice"));
                String courier = rs.getString("CourierID");
                
                transactions.add(new Transaction(
                    rs.getString("TransactionID"),
                    info, 
                    rs.getString("Status"),
                    (courier == null || courier.isEmpty()) ? "Waiting Courier" : courier
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public String generateTrxId() {
        String query = "SELECT TransactionID FROM MsTransaction ORDER BY TransactionID DESC LIMIT 1";
        ResultSet rs = db.execQuery(query);
        try {
            if (rs != null && rs.next()) {
                String lastId = rs.getString("TransactionID");
                int num = Integer.parseInt(lastId.substring(2)) + 1;
                return String.format("TR%03d", num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "TR001";
    }

    public ObservableList<Transaction> getPendingTransactions() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        String query = "SELECT * FROM MsTransaction WHERE Status = 'Pending'";
        ResultSet rs = db.execQuery(query);
        try {
            while (rs != null && rs.next()) {
                String info = "User: " + rs.getString("UserID") + " | " + rs.getDate("TransactionDate");
                
                transactions.add(new Transaction(
                    rs.getString("TransactionID"),
                    info,
                    rs.getString("Status"),
                    "Not Assigned"
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void assignCourier(String trxId, String courierId) {
        String query = String.format("UPDATE MsTransaction SET CourierID = '%s', Status = 'In Progress' WHERE TransactionID = '%s'", 
            courierId, trxId);
        db.execUpdate(query);
    }

    public ObservableList<Transaction> getCourierTransactions(String courierId) {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM MsTransaction WHERE CourierID = '%s'", courierId);
        ResultSet rs = db.execQuery(query);
        try {
            while (rs != null && rs.next()) {
                String info = "Cust: " + rs.getString("UserID") + " | " + rs.getDate("TransactionDate");
                
                transactions.add(new Transaction(
                    rs.getString("TransactionID"),
                    info,
                    rs.getString("Status"),
                    rs.getString("CourierID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void updateStatus(String trxId, String newStatus) {
        String query = String.format("UPDATE MsTransaction SET Status = '%s' WHERE TransactionID = '%s'", 
            newStatus, trxId);
        db.execUpdate(query);
    }
}