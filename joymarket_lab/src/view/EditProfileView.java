package view;

import controllers.EditProfileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Customer;

public class EditProfileView {
    private TextField txtName, txtPhone;
    private TextArea txtAddress;
    private Button btnSave, btnBack;

    public void start(Stage stage, Customer user) {
        Label title = new Label("Edit Profile");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label lblName = new Label("Full Name:");
        lblName.setStyle("-fx-text-fill: white;");
        txtName = new TextField(user.getUserName()); 

        Label lblPhone = new Label("Phone Number:");
        lblPhone.setStyle("-fx-text-fill: white;");
        txtPhone = new TextField(user.getUserPhone());

        Label lblAddress = new Label("Address:");
        lblAddress.setStyle("-fx-text-fill: white;");
        txtAddress = new TextArea(user.getUserAddress());
        txtAddress.setPrefRowCount(3);
        txtAddress.setWrapText(true);

        btnSave = new Button("Save Changes");
        btnSave.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white;");
        btnSave.setPrefWidth(150);

        btnBack = new Button("Back");
        btnBack.setStyle("-fx-background-color: white; -fx-text-fill: #3b82f6;");
        btnBack.setPrefWidth(150);

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setHgap(10);
        layout.setVgap(15);
        layout.setStyle("-fx-background-color: #1e1e1e;");

        layout.add(title, 0, 0, 2, 1);
        
        layout.add(lblName, 0, 1);
        layout.add(txtName, 1, 1);

        layout.add(lblPhone, 0, 2);
        layout.add(txtPhone, 1, 2);

        layout.add(lblAddress, 0, 3);
        layout.add(txtAddress, 1, 3);

        layout.add(btnSave, 1, 4);
        layout.add(btnBack, 1, 5);

        Scene scene = new Scene(layout, 450, 450);
        stage.setScene(scene);
        stage.setTitle("Edit Profile");
        stage.show();

        new EditProfileController(stage, this, user);
    }

    public TextField getTxtName() { return txtName; }
    public TextField getTxtPhone() { return txtPhone; }
    public TextArea getTxtAddress() { return txtAddress; }
    public Button getBtnSave() { return btnSave; }
    public Button getBtnBack() { return btnBack; }
}