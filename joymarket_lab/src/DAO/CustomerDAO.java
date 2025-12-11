package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

public class CustomerDAO {
    private Connect db = Connect.getInstance();
    ResultSet rs;

    public ObservableList<Customer> getAll() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String query = "SELECT * FROM `MsUser`";

        rs = db.execQuery(query);

        try {
            while (rs.next()) {
                Customer c = new Customer(
                    rs.getString("UserID"),
                    rs.getString("UserName"),
                    rs.getString("UserEmail"), 
                    rs.getString("UserPassword"),
                    rs.getString("UserGender"),
                    rs.getString("UserAddress"),
                    rs.getString("UserPhone"),
                    rs.getDouble("UserBalance")
                );
                
                c.setUserRole(rs.getString("UserRole")); 
                
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
    
    public Customer getCustomer(String userName) {
        String query = String.format(Locale.US, "SELECT * FROM `MsUser` WHERE `UserName` = '%s' ", userName);

        rs = db.execQuery(query);

        try {
            if (rs.next()) {
                Customer c = new Customer(rs.getString("UserID"), rs.getString("UserName"), rs.getString("UserEmail"), rs.getString("UserPassword"), rs.getString("UserGender"), rs.getString("UserAddress"), rs.getString("UserPhone"), rs.getDouble("UserBalance"));
                c.setUserRole(rs.getString("UserRole"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean insertCustomer(Customer customer) {
        String query = String.format(Locale.US, "INSERT INTO `MsUser`(`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserGender`, `UserAddress`, `UserPhone`, `UserBalance`, `UserRole`)"
        + "VALUE" + "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%f', 'Customer')",
         customer.getUserId(), customer.getUserName(), customer.getUserEmail(), customer.getUserPassword(), customer.getUserGender(), customer.getUserAddress(), customer.getUserPhone(), customer.getUserBalance());
    
        return db.execUpdate(query);
    }
    
    public boolean updateUserBalance(String userId, double amount) {
        String query = String.format(Locale.US, 
            "UPDATE `MsUser` SET `UserBalance` = `UserBalance` + %f WHERE `UserID` = '%s'", 
            amount, userId);
        
        return db.execUpdate(query);
    }
    
    public ObservableList<Customer> getAllCouriers() {
        ObservableList<Customer> couriers = FXCollections.observableArrayList();
        String query = "SELECT * FROM MsUser WHERE UserRole = 'Courier'";
        ResultSet rs = db.execQuery(query);
        try {
            while (rs != null && rs.next()) {
                Customer c = new Customer(
                    rs.getString("UserID"),
                    rs.getString("UserName"),
                    rs.getString("UserEmail"),
                    rs.getString("UserPassword"),
                    rs.getString("UserGender"),
                    rs.getString("UserAddress"),
                    rs.getString("UserPhone"),
                    rs.getDouble("UserBalance")
                );
                c.setUserRole("Courier");
                couriers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return couriers;
    }
    
    public boolean updateProfile(String userId, String newName, String newAddress, String newPhone) {
        String query = String.format("UPDATE MsUser SET UserName = '%s', UserAddress = '%s', UserPhone = '%s' WHERE UserID = '%s'", 
            newName, newAddress, newPhone, userId);
        return db.execUpdate(query);
    }
}