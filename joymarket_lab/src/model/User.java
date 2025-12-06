package model;

public abstract class User {
    protected String userId;
    protected String userName;
    protected String userEmail;
    protected String userPassword;
    protected String userGender;
    protected String userAddress;
    protected String userPhone;
    protected String userRole;

    public User(String userId, String userName, String userEmail, String userPassword, String userGender, String userAddress, String userPhone, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userRole = userRole;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getUserEmail() { return userEmail; }
    public String getUserPassword() { return userPassword; }
    public String getUserRole() { return userRole; }
    public String getUserGender() { return userGender; }
    public String getUserAddress() { return userAddress; }
    public String getUserPhone() { return userPhone; }
}