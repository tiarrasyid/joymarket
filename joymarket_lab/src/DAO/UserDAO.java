package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Admin;
import model.Courier;
import model.Customer;
import model.User;

public class UserDAO {
    private Connect db = Connect.getInstance();
    ResultSet rs;

    public ObservableList<String> getAllEmail() {

        ObservableList<String> emails = FXCollections.observableArrayList();

        String query = "SELECT * FROM `MsUser`";

        rs = db.execQuery(query);

        try {
            while (rs.next()) {
                emails.add(rs.getString("UserEmail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }

    public User getUser(String userEmail) {
        String query = String.format(Locale.US,
                "SELECT * FROM `MsUser` WHERE `UserEmail` = '%s'", userEmail);

        rs = db.execQuery(query);

        try {
            if (rs.next()) {
                if (rs.getString("UserRole").equals("Admin")) {
                    return new Admin(rs.getString("UserID"), rs.getString("UserName"), rs.getString("UserEmail"), rs.getString("UserPassword"), rs.getString("UserGender"), rs.getString("UserAddress"), rs.getString("UserPhone"));
                } else if (rs.getString("UserRole").equals("Customer")) {
                    return new Customer(rs.getString("UserID"), rs.getString("UserName"), rs.getString("UserEmail"), rs.getString("UserPassword"), rs.getString("UserGender"), rs.getString("UserAddress"), rs.getString("UserPhone"), rs.getDouble("UserBalance"));
                } else if (rs.getString("UserRole").equals("Courier")) {
                    return new Courier(rs.getString("UserID"), rs.getString("UserName"), rs.getString("UserEmail"), rs.getString("UserPassword"), rs.getString("UserGender"), rs.getString("UserAddress"), rs.getString("UserPhone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insertCustomer(Customer customer) {
        String query = String.format(Locale.US,
                "INSERT INTO `MsUser`(`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserGender`, `UserAddress`, `UserPhone`, `UserBalance`, `UserRole`)"
                        + "VALUE" + "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%f', 'Customer')",
                customer.getUserId(), customer.getUserName(), customer.getUserEmail(), customer.getUserPassword(),
                customer.getUserGender(), customer.getUserAddress(), customer.getUserPhone(),
                customer.getUserBalance());

        return db.execUpdate(query);
    }

    public boolean updateBalance(String userID, double amount) {
        String query = String.format(Locale.US, "UPDATE `MsUser` SET `UserBalance`=  %f WHERE `UserID` = \"%s\"",
                amount, userID);

        return db.execUpdate(query);
    }

    public boolean updateProfile(Customer customer) {
        String query = String.format(Locale.US,
                "UPDATE `MsUser` SET `UserName`= '%s', `UserEmail`= '%s', `UserPassword`= '%s', `UserGender`='%s', `UserAddress`= '%s', `UserPhone`= '%s' WHERE  `UserID` = \"%s\"",
                customer.getUserName(), customer.getUserEmail(), customer.getUserPassword(), customer.getUserGender(),
                customer.getUserAddress(), customer.getUserPhone(), customer.getUserId());

        return db.execUpdate(query);
    }
}
