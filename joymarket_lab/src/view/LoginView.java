package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView {

    private TextField txtEmail;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Button btnToRegister;

    public void start(Stage stage) {

        Label title = new Label("User Login");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");

        btnLogin = new Button("Login");
        btnLogin.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        btnToRegister = new Button("Register");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);

        layout.add(title, 0, 0, 2, 1);
        layout.add(new Label("Email:"), 0, 1);
        layout.add(txtEmail, 1, 1);

        layout.add(new Label("Password:"), 0, 2);
        layout.add(txtPassword, 1, 2);

        layout.add(btnLogin, 1, 3);
        layout.add(btnToRegister, 1, 4);

        Scene scene = new Scene(layout, 400, 350);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    public TextField getTxtEmail() { return txtEmail; }
    public PasswordField getTxtPassword() { return txtPassword; }
    public Button getBtnLogin() { return btnLogin; }
    public Button getBtnToRegister() { return btnToRegister; }
}
