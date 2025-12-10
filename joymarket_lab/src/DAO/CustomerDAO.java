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

        String query = "SELECT * FROM `MsUser` WHERE `UserRole` = 'Customer'";

        rs = db.execQuery(query);

        try {
            while (rs.next()) {
                customers.add(new Customer(
                    rs.getString("UserID"),
                    rs.getString("UserName"),
                    rs.getString("UserEmail"),
                    rs.getString("UserPassword"),
                    rs.getString("UserGender"),
                    rs.getString("UserAddress"),
                    rs.getString("UserPhone"),
                    rs.getDouble("UserBalance")
                ));
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
                return new Customer(rs.getString("UserID"), rs.getString("UserName"), rs.getString("UserEmail"), rs.getString("UserPassword"), rs.getString("UserGender"), rs.getString("UserAddress"), rs.getString("UserPhone"), rs.getDouble("UserBalance"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
}
