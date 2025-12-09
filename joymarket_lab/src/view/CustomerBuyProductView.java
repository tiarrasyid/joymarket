package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerBuyProductView {

    private TextField txtCount;
    private Label lblError;
    private Button btnAdd;
    private Button btnBack;

    private final String fieldStyle = "-fx-text-fill: white; -fx-control-inner-background: #2b2b2b;";
    private final int fieldWidth = 220;

    public void start(Stage stage) {

        Label title = new Label("Buy Product");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane.setHalignment(title, HPos.CENTER);

        Label subtitle = new Label("Masukkan jumlah barang ke keranjang");
        subtitle.setStyle("-fx-text-fill: #cfcfcf; -fx-font-size: 12px;");
        GridPane.setHalignment(subtitle, HPos.CENTER);


        txtCount = createTextField("Jumlah yang ingin dibeli");

        lblError = new Label();
        lblError.setStyle("-fx-text-fill: #ef4444; -fx-font-size: 12px;");

        btnAdd = createMainButton("Add to Cart");
        btnBack = createSecondaryButton("Back to Menu");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(25));
        layout.setHgap(10);
        layout.setVgap(12);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e;");

        int row = 0;

        layout.add(title, 0, row++, 2, 1);
        layout.add(subtitle, 0, row++, 2, 1);

        layout.add(createLabel("Count:"), 0, row);
        layout.add(txtCount, 1, row++);

        layout.add(lblError, 1, row++);

        layout.add(btnAdd, 1, row++);
        layout.add(btnBack, 1, row++);

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Buy Product");
        stage.show();
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
        label.setStyle("-fx-text-fill: white;");
        return label;
    }

    private Button createMainButton(String text) {
        Button btn = new Button(text);
        btn.setStyle(
            "-fx-background-color: #3b82f6; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 8;"
        );
        btn.setPrefWidth(fieldWidth);
        return btn;
    }

    private Button createSecondaryButton(String text) {
        Button btn = new Button(text);
        btn.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: #3b82f6; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 8;"
        );
        btn.setPrefWidth(fieldWidth);
        return btn;
    }

    public TextField getTxtCount() { return txtCount; }
    public Label getLblError() { return lblError; }
    public Button getBtnAdd() { return btnAdd; }
    public Button getBtnBack() { return btnBack; }
}
