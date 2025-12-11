package controllers;

import java.util.HashMap;
import DAO.CustomerDAO;
import DAO.TransactionDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Customer;
import model.Transaction;
import model.User;
import view.AdminMenuView;
import view.AssignCourierView;

public class AssignCourierController {
    private Stage stage;
    private AssignCourierView view;
    private User currentUser;
    
    private TransactionDAO trxDAO = new TransactionDAO();
    private CustomerDAO userDAO = new CustomerDAO();
    
    private HashMap<String, String> courierMap = new HashMap<>();

    public AssignCourierController(Stage stage, AssignCourierView view, User currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        
        loadData();
        initAction();
    }

    private void loadData() {
        // 1. Load Tabel Transaksi Pending
        view.getTable().setItems(trxDAO.getPendingTransactions());

        // 2. Load ComboBox Kurir
        ObservableList<Customer> couriers = userDAO.getAllCouriers();
        for (Customer c : couriers) {
            String label = c.getUserName() + " (" + c.getUserId() + ")";
            view.getCbCouriers().getItems().add(label);
            
            courierMap.put(label, c.getUserId());
        }
    }

    private void initAction() {
        view.getBtnAssign().setOnAction(e -> handleAssign());
        view.getBtnBack().setOnAction(e -> {
            AdminMenuView menu = new AdminMenuView();
            menu.start(stage, currentUser);
        });
    }

    private void handleAssign() {
        // 1. Cek apakah ada transaksi yang dipilih
        Transaction selectedTrx = view.getTable().getSelectionModel().getSelectedItem();
        if (selectedTrx == null) {
            showAlert("Error", "Pilih transaksi dari tabel dulu!");
            return;
        }

        // 2. Cek apakah kurir sudah dipilih
        String selectedCourierLabel = view.getCbCouriers().getValue();
        if (selectedCourierLabel == null) {
            showAlert("Error", "Pilih kurir dulu!");
            return;
        }

        // 3. Ambil ID Kurir dari Map
        String courierId = courierMap.get(selectedCourierLabel);

        // 4. Update Database
        trxDAO.assignCourier(selectedTrx.getTransactionId(), courierId);

        showAlert("Success", "Berhasil assign kurir!");
        
        // Refresh Tabel
        view.getTable().setItems(trxDAO.getPendingTransactions());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}