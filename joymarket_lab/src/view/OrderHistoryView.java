package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class OrderHistoryView {

    private Button btnBack;
    private VBox orderListContainer;

    public void start(Stage stage) {

        // Title
        Label title = new Label("Order History");
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 22px");
        GridPane.setHalignment(title, HPos.CENTER);

        Label subtitle = new Label("Riwayat pesanan terbaru Anda");
        subtitle.setStyle("-fx-text-fill: #cfcfcf; -fx-font-size: 12px;");
        GridPane.setHalignment(subtitle, HPos.CENTER);

        // Container for orders
        orderListContainer = new VBox(12);
        orderListContainer.setPadding(new Insets(10));
        orderListContainer.setAlignment(Pos.TOP_LEFT);

        // Sample order items
        orderListContainer.getChildren().add(createOrderItem(
                "test", "test", "Product 1", 125000, 10, "--"
        ));

        orderListContainer.getChildren().add(createOrderItem(
                "test", "test", "Product 2", 99000, 5, "--"
        ));

        // Back Button (centered)
        btnBack = new Button("Back");
        btnBack.setPrefWidth(200);
        btnBack.setStyle(
                "-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-padding: 10 20; -fx-background-radius: 10;"
        );
        GridPane.setHalignment(btnBack, HPos.CENTER);

        // Layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(25));
        layout.setVgap(15);
        layout.setHgap(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #1e1e1e");

        int row = 0;
        layout.add(title, 0, row++, 2, 1);
        layout.add(subtitle, 0, row++, 2, 1);

        layout.add(orderListContainer, 0, row++, 2, 1);

        // Back button centered
        layout.add(btnBack, 0, row++, 2, 1);

        Scene scene = new Scene(layout, 650, 600);
        stage.setScene(scene);
        stage.setTitle("Order History");
        stage.show();
    }

    private VBox createOrderItem(String idOrder, String idProduct, String name, int price, int stock, String category) {
        VBox box = new VBox(4);
        box.setPadding(new Insets(12));
        box.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 10;");
        box.setPrefWidth(520);

        box.getChildren().addAll(
                createLabel("Order ID: " + idOrder),
                createLabel("Product ID: " + idProduct),
                createLabel("Name: " + name),
                createLabel("Price: Rp " + price),
                createLabel("Stock: " + stock),
                createLabel("Category: " + category)
        );

        return box;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        return label;
    }

    public Button getBtnBack() { return btnBack; }
}
