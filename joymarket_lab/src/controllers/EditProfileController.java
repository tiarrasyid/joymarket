package controllers;

import DAO.UserDAO;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;
import view.EditProfileView;
import view.MainMenuView;

public class EditProfileController {
    private Stage stage;
    private EditProfileView view;
    private Customer currentUser;
    private UserDAO uDAO = new UserDAO();

    public EditProfileController(Stage stage, EditProfileView view, Customer currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        initAction();
    }

    private void initAction() {
        view.getBtnSave().setOnAction(e -> handleSave());
        view.getBtnBack().setOnAction(e -> {
            MainMenuView menu = new MainMenuView();
            menu.start(stage, currentUser);
        });
    }

    private void handleSave() {
        String newName = view.getTxtName().getText().trim();
        String newPhone = view.getTxtPhone().getText().trim();
        String newAddress = view.getTxtAddress().getText().trim();

        // VALIDASI 1: Tidak boleh kosong [cite: 59]
        if (newName.isEmpty() || newPhone.isEmpty() || newAddress.isEmpty()) {
            showAlert("Error", "Semua kolom harus diisi!");
            return;
        }

        // VALIDASI 2: Telepon harus Angka (Manual Loop - NO REGEX) [cite: 91]
        boolean isNumeric = true;
        for (char c : newPhone.toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumeric = false;
                break;
            }
        }
        if (!isNumeric) {
            showAlert("Error", "Nomor telepon harus angka!");
            return;
        }

        // VALIDASI 3: Telepon 10-13 digit [cite: 59]
        if (newPhone.length() < 10 || newPhone.length() > 13) {
            showAlert("Error", "Nomor telepon harus 10-13 digit!");
            return;
        }

        // UPDATE DATABASE
        if (uDAO.updateProfile(currentUser.getUserId(), newName, newAddress, newPhone)) {
            // Update Object User di Memory (biar pas balik ke menu, nama baru langsung muncul)
            currentUser.setUserName(newName);
            currentUser.setUserPhone(newPhone);
            currentUser.setUserAddress(newAddress);

            showAlert("Success", "Profile berhasil diupdate!");
            
            // Balik ke Main Menu
            MainMenuView menu = new MainMenuView();
            menu.start(stage, currentUser);
        } else {
            showAlert("Error", "Gagal update database.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}