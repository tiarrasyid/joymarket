package model;

public class Customer extends User {
    // Sisakan variabel yang KHUSUS Customer saja
    private double userBalance;

    public Customer(String userId, String userName, String userEmail, String userPassword, String userGender, String userAddress, String userPhone, double userBalance) {
        // Oper semua data umum ke constructor User (Parent)
        super(userId, userName, userEmail, userPassword, userGender, userAddress, userPhone, "Customer");
        
        this.userBalance = userBalance;
    }

    public double getUserBalance() { return userBalance; }
    public void setUserBalance(double userBalance) { this.userBalance = userBalance; }
}