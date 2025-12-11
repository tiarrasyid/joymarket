package controllers;

import DAO.TransactionDAO;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Customer;
import model.Transaction;
import view.CourierStatusView;
import view.MainMenuView;

public class CourierStatusController {
    private Stage stage;
    private CourierStatusView view;
    private Customer currentUser;    
    private TransactionDAO tDAO = new TransactionDAO();

    public CourierStatusController(Stage stage, CourierStatusView view, Customer currentUser) {
        this.stage = stage;
        this.view = view;
        this.currentUser = currentUser;
        
        loadData();
        initAction();
    }

    private void loadData() {
        ObservableList<Transaction> data = tDAO.getUserTransactions(currentUser.getUserId());
        
        view.getTable().setItems(data);
    }

    private void initAction() {
        view.getBtnBack().setOnAction(e -> {
            MainMenuView mainMenu = new MainMenuView();
            mainMenu.start(stage, currentUser);
        });
    }
}