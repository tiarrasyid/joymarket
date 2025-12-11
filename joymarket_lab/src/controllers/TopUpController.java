package controllers;

import java.util.Locale;

import DAO.UserDAO;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;
import view.CustomerTopUpView;
import view.MainMenuView;

public class TopUpController {
    private Stage stage;
    private CustomerTopUpView view;
    private Customer currentUser;
    private UserDAO uDAO = new UserDAO();

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
        
        boolean isNumeric = true;
        for (char c : amountStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumeric = false;
                break;
            }
        }


        if (amountStr.isEmpty() || !isNumeric) {
            view.setLblError("Masukkan nominal angka saja!");
            return;
        }

        double amount = Double.parseDouble(amountStr);

        if (amount < 10000) {
            view.setLblError("Minimal top up Rp 10.000");;
            return;
        }

        amount += currentUser.getUserBalance();

        // Proses update ke database
        if (uDAO.updateBalance(currentUser.getUserId(), amount)) {
            // Update saldo di object local juga biar sinkron
            currentUser.setUserBalance(amount);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(String.format(Locale.US, "Top Up Berhasil! Saldo bertambah: %,.0f", amount));
            alert.showAndWait();
            
            handleBack();
        } else {
            view.setLblError("Gagal koneksi database");;
        }
    }

    private void handleBack() {
        MainMenuView mainMenu = new MainMenuView();
        mainMenu.start(stage, currentUser);
    }
}
