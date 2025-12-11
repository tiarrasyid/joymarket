package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerEditProfileView {

    private TextField txtFullName;
    private TextField txtPhone;
    private TextArea txtAddress;

    private Label lblError;

    private Button btnSave;
    private Button btnBack;

    private final String fieldStyle = "-fx-text-fill: white; -fx-control-inner-background: #2b2b2b;";
    private final int fieldWidth = 260;

    public void start(Stage stage) {


        Label title = new Label("Edit Profile");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 22px; -fx-font-weight: bold;");
        GridPane.setHalignment(title, HPos.CENTER);

        Label subtitle = new Label("Ganti informasi personal kamu");
        subtitle.setStyle("-fx-text-fill: #cfcfcf; -fx-font-size: 12px;");
        GridPane.setHalignment(subtitle, HPos.CENTER);


        txtFullName = new TextField();
        txtFullName.setPromptText("Full Name");
        txtFullName.setStyle(fieldStyle);
        txtFullName.setPrefWidth(fieldWidth);

        txtPhone = new TextField();
        txtPhone.setPromptText("Phone Number (10–13 digits)");
        txtPhone.setStyle(fieldStyle);
        txtPhone.setPrefWidth(fieldWidth);

        txtAddress = new TextArea();
        txtAddress.setPromptText("Address");
        txtAddress.setStyle(fieldStyle);
        txtAddress.setPrefRowCount(3);
        txtAddress.setPrefWidth(fieldWidth);

        
        lblError = new Label();
        lblError.setStyle("-fx-text-fill: #ef4444; -fx-font-size: 12px;");

        btnSave = new Button("Save Changes");
        btnSave.setStyle(
            "-fx-background-color: #3b82f6; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 8;"
        );
        btnSave.setPrefWidth(fieldWidth);


        btnBack = new Button("Back");
        btnBack.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: #3b82f6; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 8;"
        );
        btnBack.setPrefWidth(fieldWidth);


        GridPane layout = new GridPane();
        layout.setPadding(new Insets(30));
        layout.setHgap(10);
        layout.setVgap(12);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e;");

        int row = 0;

        layout.add(title, 0, row++, 2, 1);
        layout.add(subtitle, 0, row++, 2, 1);

        layout.add(createLabel("Full Name:"), 0, row);
        layout.add(txtFullName, 1, row++);

        layout.add(createLabel("Phone:"), 0, row);
        layout.add(txtPhone, 1, row++);

        layout.add(createLabel("Address:"), 0, row);
        layout.add(txtAddress, 1, row++);

        layout.add(lblError, 1, row++);

        layout.add(btnSave, 1, row++);
        layout.add(btnBack, 1, row++);

        Scene scene = new Scene(layout, 520, 500);
        stage.setScene(scene);
        stage.setTitle("Edit Profile");
        stage.show();
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white;");
        return label;
    }
    
    public TextField getTxtFullName() { return txtFullName; }
    public TextField getTxtPhone() { return txtPhone; }
    public TextArea getTxtAddress() { return txtAddress; }
    public Label getLblError() { return lblError; }
    public Button getBtnSave() { return btnSave; }
    public Button getBtnBack() { return btnBack; }
}
