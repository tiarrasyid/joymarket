package view;

import javafx.geometry.*;
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
    
    private final String fieldStyle = "-fx-text-fill: white; -fx-control-inner-background: #2b2b2b";
    private final int fieldWidth = 220;
    
    public void start(Stage stage) {

        Label title = new Label("Customer Registration");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white");
        GridPane.setHalignment(title, javafx.geometry.HPos.CENTER);

        
        // field
        txtCustomerId = createTextField("Customer ID");
        txtCustomerId.setDisable(true);
        txtCustomerId.setStyle(fieldStyle + "; -fx-opacity: 0.8;");
        
        txtFullName = createTextField("Full Name");
        
        txtEmail = createTextField("Email (blabla@example.co)");
        
        txtPassword = createPasswordField("Password");
        
        txtConfirmPassword = createPasswordField("Confirm Password");
        
        txtPhone = createTextField("Phone Number");
        	
        txtAddress = new TextArea();
        txtAddress.setPromptText("Address");
        txtAddress.setPrefRowCount(3);
        txtAddress.setStyle(fieldStyle);
        txtAddress.setPrefWidth(fieldWidth);
        
        cbGender = new ComboBox<>();
        cbGender.getItems().addAll("Male", "Female", "Other");
        cbGender.setPromptText("Select gender");
        cbGender.setStyle(fieldStyle);
        cbGender.setPrefWidth(fieldWidth);
        
        // button
        btnRegister = new Button("Register");
        btnRegister.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-padding: 10 20; -fx-background-radius: 8;");

        btnBackToLogin = new Button("Back to Login");
        btnBackToLogin.setStyle("-fx-background-color: white; -fx-text-fill: #3B82F6; -fx-padding: 10 20; -fx-background-radius: 8;");
        
     

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(25));
        layout.setVgap(12);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e");

        layout.add(title, 0, 0, 2, 1);

        layout.add(createLabel("Customer ID:"), 0, 1);
        layout.add(txtCustomerId, 1, 1);

        layout.add(createLabel("Full Name:"), 0, 2);
        layout.add(txtFullName, 1, 2);

        layout.add(createLabel("Email:"), 0, 3);
        layout.add(txtEmail, 1, 3);

        layout.add(createLabel("Password:"), 0, 4);
        layout.add(txtPassword, 1, 4);

        layout.add(createLabel("Confirm Password:"), 0, 5);
        layout.add(txtConfirmPassword, 1, 5);

        layout.add(createLabel("Phone:"), 0, 6);
        layout.add(txtPhone, 1, 6);

        layout.add(createLabel("Address:"), 0, 7);
        layout.add(txtAddress, 1, 7);

        layout.add(createLabel("Gender:"), 0, 8);
        layout.add(cbGender, 1, 8);

        layout.add(btnRegister, 1, 9);
        layout.add(btnBackToLogin, 1, 10);

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Customer Register Page");
        stage.show();
    }
    
    private TextField createTextField(String placeholder) {
    	TextField tf = new TextField();
    	tf.setPromptText(placeholder);
    	tf.setStyle(fieldStyle);
    	tf.setPrefWidth(fieldWidth);
    	return tf;
    }
    
    private PasswordField createPasswordField(String placeholder) {
    	PasswordField pf = new PasswordField();
    	pf.setPromptText(placeholder);
    	pf.setStyle(fieldStyle);
    	pf.setPrefWidth(fieldWidth);
    	return pf;
    }
    
    private Label createLabel(String text) {
    	Label label = new Label(text);
    	label.setStyle("-fx-text-fill: white;");
    	return label;
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
