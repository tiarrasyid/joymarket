package model;

public class Admin extends User {
    public Admin(String userId, String userName, String userEmail, String userPassword, String userGender, String userAddress, String userPhone) {
        super(userId, userName, userEmail, userPassword, userGender, userAddress, userPhone, "Admin");
    }
}