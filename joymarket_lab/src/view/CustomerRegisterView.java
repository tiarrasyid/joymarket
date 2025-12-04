package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerRegisterView {

    private TextField txtCustomerId;
    private TextField txtFullName;
    private TextField txtEmail;
    private PasswordField txtPassword;
    private PasswordField txtConfirmPassword;
    private TextField txtPhone;
    private TextArea txtAddress;
    private ComboBox<String> cbGender;
    private Button btnRegister;
    private Button btnBackToLogin;

    public void start(Stage stage) {

        Label title = new Label("Customer Registration");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        txtCustomerId = new TextField();
        txtCustomerId.setPromptText("Customer ID"); 
        txtCustomerId.setDisable(true); 
        txtCustomerId.setStyle("-fx-opacity: 0.8;");

        txtFullName = new TextField();
        txtFullName.setPromptText("Full Name");

        txtEmail = new TextField();
        txtEmail.setPromptText("Email (example@gmail.com)");

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");

        txtConfirmPassword = new PasswordField();
        txtConfirmPassword.setPromptText("Confirm Password");

        txtPhone = new TextField();
        txtPhone.setPromptText("Phone Number");

        txtAddress = new TextArea();
        txtAddress.setPromptText("Address");
        txtAddress.setPrefRowCount(3);

        cbGender = new ComboBox<>();
        cbGender.getItems().addAll("Male", "Female", "Other");
        cbGender.setPromptText("Select gender");

        btnRegister = new Button("Register");
        btnRegister.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        btnBackToLogin = new Button("Back to Login");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);

        layout.add(title, 0, 0, 2, 1);
        layout.add(new Label("Customer ID:"), 0, 1);
        layout.add(txtCustomerId, 1, 1);

        layout.add(new Label("Full Name:"), 0, 2);
        layout.add(txtFullName, 1, 2);

        layout.add(new Label("Email:"), 0, 3);
        layout.add(txtEmail, 1, 3);

        layout.add(new Label("Password:"), 0, 4);
        layout.add(txtPassword, 1, 4);

        layout.add(new Label("Confirm Password:"), 0, 5);
        layout.add(txtConfirmPassword, 1, 5);

        layout.add(new Label("Phone:"), 0, 6);
        layout.add(txtPhone, 1, 6);

        layout.add(new Label("Address:"), 0, 7);
        layout.add(txtAddress, 1, 7);

        layout.add(new Label("Gender:"), 0, 8);
        layout.add(cbGender, 1, 8);

        layout.add(btnRegister, 1, 9);
        layout.add(btnBackToLogin, 1, 10);

        Scene scene = new Scene(layout, 450, 600);
        stage.setScene(scene);
        stage.setTitle("Customer Register");
        stage.show();
    }

    public TextField getTxtCustomerId() { return txtCustomerId; }
    public TextField getTxtFullName() { return txtFullName; }
    public TextField getTxtEmail() { return txtEmail; }
    public PasswordField getTxtPassword() { return txtPassword; }
    public PasswordField getTxtConfirmPassword() { return txtConfirmPassword; }
    public TextField getTxtPhone() { return txtPhone; }
    public TextArea getTxtAddress() { return txtAddress; }
    public ComboBox<String> getCbGender() { return cbGender; }
    public Button getBtnRegister() { return btnRegister; }
    public Button getBtnBackToLogin() { return btnBackToLogin; }
}
