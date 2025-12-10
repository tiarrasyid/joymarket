package model;

public class User {
    protected String userId;
    protected String userName;
    protected String userEmail;
    protected String userPassword;
    protected String userGender;   // Pindah ke sini
    protected String userAddress;  // Pindah ke sini
    protected String userPhone;    // Pindah ke sini
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

    public User() {}

    // --- GETTER & SETTER LENGKAP ---

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public String getUserGender() { return userGender; }
    public void setUserGender(String userGender) { this.userGender = userGender; }

    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}