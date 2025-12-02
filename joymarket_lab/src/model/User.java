package model;

public class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userRole;
    private double userBalance;

    public User(String userId, String userName, String userEmail, String userRole, double userBalance) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userBalance = userBalance;
    }

    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getUserRole() { return userRole; }
    public double getUserBalance() { return userBalance; }
}