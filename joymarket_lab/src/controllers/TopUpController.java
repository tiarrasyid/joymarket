package controllers;

import DAO.CustomerDAO;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;
import view.CustomerTopUpView;
import view.MainMenuView;

public class TopUpController {
    private Stage stage;
    private CustomerTopUpView view;
    private Customer currentUser;
    private CustomerDAO cDAO = new CustomerDAO();

    public TopUpController(Stage stage, CustomerTopUpView view, Customer currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        initAction();
    }

    private void initAction() {
        view.getBtnTopUp().setOnAction(e -> handleTopUp());
        view.getBtnBack().setOnAction(e -> handleBack());
    }

    private void handleTopUp() {
        String amountStr = view.getTxtAmount().getText();
        
        // --- VALIDASI MANUAL PENGGANTI REGEX ---
        boolean isNumeric = true;
        for (char c : amountStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumeric = false;
                break;
            }
        }
        // ---------------------------------------

        // Validasi input angka
        if (amountStr.isEmpty() || !isNumeric) {
            view.getLblError().setText("Masukkan nominal angka saja!");
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Validasi minimal 10.000
        if (amount < 10000) {
            view.getLblError().setText("Minimal top up Rp 10.000");
            return;
        }

        // Proses update ke database
        if (cDAO.updateUserBalance(currentUser.getUserId(), amount)) {
            // Update saldo di object local juga biar sinkron
            currentUser.setUserBalance(currentUser.getUserBalance() + amount);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Top Up Berhasil! Saldo bertambah: " + amount);
            alert.showAndWait();
            
            // Kembali ke Main Menu
            handleBack();
        } else {
            view.getLblError().setText("Gagal koneksi database");
        }
    }

    private void handleBack() {
        MainMenuView mainMenu = new MainMenuView();
        mainMenu.start(stage, currentUser);
    }
}