package view;

import controllers.TopUpController;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Customer;

public class CustomerTopUpView {
    private TextField txtAmount;
    private Button btnTopup;
    private Button btnBack;
    private Label lblError;
    
    private final String fieldStyle = "-fx-text-fill: white; -fx-control-inner-background: #2b2b2b";
    private final int fieldWidth = 220;
    
    public void start(Stage stage, Customer user) {
        Label title = new Label("Top Up");
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20px");
        GridPane.setHalignment(title, HPos.CENTER);
        
        Label subtitle = new Label("Masukkan nominal saldo yang mau di top-up: (min 10.000)");
        subtitle.setStyle("-fx-text-fill: #cfcfcf; -fx-font-size: 12px;");
        GridPane.setHalignment(subtitle, HPos.CENTER);
        
        txtAmount = createTextField("Masukkin nominal yang mau di top-up: ");
        
        lblError = new Label();
        lblError.setStyle("-fx-text-fill: #ef4444; -fx-font-size: 12px");
        
        btnTopup = new Button("Top Up");
        btnTopup.setPrefWidth(200);
        btnTopup.setStyle(
            "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8;"
        );
        btnTopup.setOnMouseEntered(e -> btnTopup.setStyle(
            "-fx-background-color: #5592ff; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8;"
        ));
        btnTopup.setOnMouseExited(e -> btnTopup.setStyle(
            "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8;"
        ));

        btnBack = new Button("Back to Menu");
        btnBack.setPrefWidth(200);
        btnBack.setStyle(
            "-fx-background-color: #ffffff; -fx-text-fill: #3b82f6; -fx-font-size: 14px; -fx-background-radius: 8;"
        );
        btnBack.setOnMouseEntered(e -> btnBack.setStyle(
            "-fx-background-color: #e8e8e8; -fx-text-fill: #3b82f6; -fx-font-size: 14px; -fx-background-radius: 8;"
        ));
        btnBack.setOnMouseExited(e -> btnBack.setStyle(
            "-fx-background-color: #ffffff; -fx-text-fill: #3b82f6; -fx-font-size: 14px; -fx-background-radius: 8;"
        ));     
        
        
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(25));
        layout.setVgap(12);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e");
        
        int row = 0;
        
        layout.add(title, 0, row++, 2, 1);
        layout.add(subtitle, 0, row++, 2, 1);

        layout.add(createLabel("Amount:"), 0, row);
        layout.add(txtAmount, 1, row++);

        layout.add(lblError, 1, row++);

        layout.add(btnTopup, 1, row++);
        layout.add(btnBack, 1, row++);
        
        Scene scene = new Scene(layout, 450, 420);
        stage.setScene(scene);
        stage.setTitle("Top Up Balance");
        stage.show();
        
        new TopUpController(stage, this, user);
    }
    
    private TextField createTextField(String placeholder) {
        TextField tf = new TextField();
        tf.setPromptText(placeholder);
        tf.setStyle(fieldStyle);
        tf.setPrefWidth(fieldWidth);
        return tf;
    }
    
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white");
        return label;
    }
    
    public TextField getTxtAmount() { return txtAmount; }
    public Button getBtnTopUp() { return btnTopup; }
    public Button getBtnBack() { return btnBack; }
    public Label getLblError() { return lblError; }
}