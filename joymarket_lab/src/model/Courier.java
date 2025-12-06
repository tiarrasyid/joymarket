package model;

public class Courier extends User {
    public Courier(String userId, String userName, String userEmail, String userPassword, String userGender, String userAddress, String userPhone) {
        super(userId, userName, userEmail, userPassword, userGender, userAddress, userPhone, "Courier");
    }
}
