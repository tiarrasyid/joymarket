package controllers;

import DAO.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import model.Customer;
import model.User;
import view.AdminMenuView; // Import Menu Admin
import view.CustomerRegisterView;
import view.LoginView;
import view.MainMenuView; // Import Menu Customer

public class LoginController {
    private Stage stage;
    private LoginView loginView;
    private CustomerRegisterView registerView;

    ObservableList<Customer> customers = FXCollections.observableArrayList();
    CustomerDAO cDAO = new CustomerDAO();

    public LoginController(Stage stage, LoginView loginView, CustomerRegisterView registerView) {
        this.stage = stage;
        this.loginView = loginView;
        this.registerView = registerView;
        this.customers = cDAO.getAll(); 
        initAction();
    }

    private void initAction() {
        if (loginView != null) {
            loginView.getBtnLogin().setOnAction(e -> handleLogin());
            loginView.getBtnToRegister().setOnAction(e -> {
                CustomerRegisterView registView = new CustomerRegisterView();
                registView.start(stage);
            });
        } else {
            registerView.getBtnRegister().setOnAction(e -> handleRegister());
            registerView.getBtnBackToLogin().setOnAction(e -> {
                LoginView logView = new LoginView();
                logView.start(stage);
            });
        }
    }

    private void handleLogin() {
        String emailString = loginView.getTxtEmail().getText();
        String passwordString = loginView.getTxtPassword().getText();

        User user = validationLoginInput(emailString, passwordString);

        if (user == null) {
            return;
        }

        System.out.println("Login Successfully as " + user.getUserRole());

        // --- ROLE BASED NAVIGATION ---
        if (user.getUserRole().equalsIgnoreCase("Admin")) {
            // Jika Admin, buka menu Admin
            AdminMenuView adminMenu = new AdminMenuView();
            adminMenu.start(stage, user);
            
        } else if (user.getUserRole().equalsIgnoreCase("Customer")) {
            // Jika Customer, buka menu Customer
            MainMenuView customerMenu = new MainMenuView();
            customerMenu.start(stage, user);
            
        } else if (user.getUserRole().equalsIgnoreCase("Courier")) {
        	view.CourierMenuView courierMenu = new view.CourierMenuView();
            courierMenu.start(stage, user);
        }
    }

    private void handleRegister() {
        String fullNameString = registerView.getTxtFullName().getText().trim();
        String emailString = registerView.getTxtEmail().getText().trim();
        String passwordString = registerView.getTxtPassword().getText().trim();
        String confirmPasswordString = registerView.getTxtConfirmPassword().getText().trim();
        String phoneString = registerView.getTxtPhone().getText().trim();
        String addressString = registerView.getTxtAddress().getText().trim();
        String gendeString = registerView.getCbGender().getValue();

        if (!validationRegisterInput(fullNameString, emailString, passwordString, confirmPasswordString, phoneString,
                addressString, gendeString, customers)) {
            return;
        }

        int index = customers.size() + 1;
        Customer newCustomer = new Customer(String.format("CS%03d", index), fullNameString, emailString, passwordString,
                gendeString, addressString, phoneString, 0);
        
        if (cDAO.insertCustomer(newCustomer)) {
            System.out.println("Register Successfully");
            MainMenuView user = new MainMenuView();
            user.start(stage, newCustomer);
        }
    }

    private boolean validationRegisterInput(String fullNameString, String emailString, String passwordString, String confirmPasswordString, String phoneString, String addressString, String gendeString, ObservableList<Customer> customers) {
        boolean emailIsExist = customers.stream().anyMatch(c -> c.getUserEmail().equalsIgnoreCase(emailString));

        // --- VALIDASI MANUAL PENGGANTI REGEX (Sesuai Soal) ---
        boolean isNumeric = true;
        for (char c : phoneString.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumeric = false;
                break;
            }
        }
        // -----------------------------------------------------

        if (fullNameString.isEmpty() || fullNameString.length() < 1) {
            errorAlert("Masukan nama", registerView.getTxtFullName());
            return false;
        } else if (emailString.isEmpty() || !emailString.endsWith("@gmail.com")) {
            errorAlert("Masukan email dengan benar (berakhiran '@gmail.com')", registerView.getTxtEmail());
            return false;
        } else if (emailIsExist) {
            errorAlert("Email sudah terdaftar, coba gunakan email lain", registerView.getTxtEmail());
            return false;
        } else if (passwordString.isEmpty() || passwordString.length() < 6
                || !passwordString.equals(confirmPasswordString)) {
            errorAlert("Masukan password (Min:6 characters) dan harus sama persis dengan Confirm Password",
                    registerView.getTxtPassword());
            return false;
        } else if (phoneString.isEmpty() || phoneString.length() < 10 || phoneString.length() > 13
                || !isNumeric) { // Cek numerik manual
            errorAlert("Format telepon : 10-13 digit, numeric only", registerView.getTxtPhone());
            return false;
        } else if (addressString.isEmpty()) {
            errorAlert("Masukan alamat", registerView.getTxtAddress());
            return false;
        } else if (gendeString == null) {
            errorAlert("Pilih gender", registerView.getCbGender());
            return false;
        }

        return true;
    }

    private User validationLoginInput(String email, String password) {
        if (!email.endsWith("@gmail.com")) {
            errorAlert("Masukan email dengan benar (berakhiran @gmail.com)", loginView.getTxtEmail());
            return null;
        } else if (password.length() < 6) {
            errorAlert("Masukan password dengan benar (Min:6 character)", loginView.getTxtPassword());
            return null;
        }

        Customer existingCustomer = customers.stream()
                                    .filter(c -> c.getUserEmail().equalsIgnoreCase(email))
                                    .findFirst()
                                    .orElse(null);

        // Logic login juga berlaku untuk Admin/Courier karena mereka ada di tabel MsUser yang sama
        // (Diasumsikan CustomerDAO.getAll() mengambil semua user dari MsUser)
        
        if (existingCustomer == null) {
            errorAlert("Akun tidak ditemukan", loginView.getTxtEmail());
            return null;
        } else if (!existingCustomer.getUserPassword().equals(password)) {
            errorAlert("Password salah", loginView.getTxtPassword());
            return null;
        }

        return existingCustomer;
    }

    private void errorAlert(String message, Control field) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validasi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        field.requestFocus();
    }
}