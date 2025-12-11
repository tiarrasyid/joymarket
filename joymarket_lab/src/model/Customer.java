package model;

public class Customer extends User {
    private double userBalance;

    public Customer(String userId, String userName, String userEmail, String userPassword, String userGender, String userAddress, String userPhone, double userBalance) {
        super(userId, userName, userEmail, userPassword, userGender, userAddress, userPhone, "Customer");
        
        this.userBalance = userBalance;
    }

    public double getUserBalance() { return userBalance; }
    public void setUserBalance(double userBalance) { this.userBalance = userBalance; }
}