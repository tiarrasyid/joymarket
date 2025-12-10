package view;

import controllers.LoginController;
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
    
    private final String fieldStyle = "-fx-text-fill: white; -fx-control-inner-background: #2b2b2b";
    private final int fieldWidth = 200;

    public void start(Stage stage) {

        Label title = new Label("User Login");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        GridPane.setHalignment(title, HPos.CENTER);

        txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        txtEmail.setStyle(fieldStyle);
        txtEmail.setPrefWidth(fieldWidth);

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");
        txtPassword.setStyle(fieldStyle);
        txtPassword.setPrefWidth(fieldWidth);

        btnLogin = new Button("Login");
        btnLogin.setStyle("-fx-background-color: #3B82F6; -fx-text-fill: white;");

        btnToRegister = new Button("Register");
        btnToRegister.setStyle("-fx-background-color: white; -fx-text-fill: #3B82F6");

        GridPane layout = new GridPane();
        layout.setStyle("-fx-background-color: #1e1e1e");
        
        layout.setPadding(new Insets(20));
        layout.setVgap(12);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);

        
        Label lblEmail = new Label("Email:");
        lblEmail.setStyle("-fx-text-fill: white;");      

        Label lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-text-fill: white;");    
        
        layout.add(title, 0, 0, 2, 1);
        layout.add(lblEmail, 0, 1);
        
        layout.add(txtEmail, 1, 1);

        layout.add(lblPassword, 0, 2);
        layout.add(txtPassword, 1, 2);

        layout.add(btnLogin, 1, 3);
        layout.add(btnToRegister, 1, 4);

        Scene scene = new Scene(layout, 400, 350);
        new LoginController(stage, this, null);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    public TextField getTxtEmail() { return txtEmail; }
    public PasswordField getTxtPassword() { return txtPassword; }
    public Button getBtnLogin() { return btnLogin; }
    public Button getBtnToRegister() { return btnToRegister; }
}
