package view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProductListView {

    public void start(Stage stage) {

        Label title = new Label("Browse Products");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox.setMargin(title, new Insets(0, 0, 20, 0));

        // produk sementara
        Product[] products = new Product[] {
            new Product("Produk 1", 120000, 10),
            new Product("Produk 2", 95000, 7)
        };


        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < products.length; i++) {
            grid.add(createProductCard(products[i]), i % 2, i / 2);
        }

        Button btnBack = new Button("Back");
        btnBack.setStyle(
            "-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold; " +
            "-fx-padding: 10 20; -fx-background-radius: 10;"
        );
        VBox.setMargin(btnBack, new Insets(10, 0, 0, 0));
        btnBack.setOnAction(e -> stage.close());

        VBox layout = new VBox(25, title, grid, btnBack);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #1e1e1e;");

        Scene scene = new Scene(layout, 750, 600);
        stage.setScene(scene);
        stage.setTitle("Product List");
        stage.show();
    }

    
    // card tempplate
    private VBox createProductCard(Product product) {

        Label name = new Label(product.getName());
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label price = new Label("Price: Rp " + product.getPrice());
        price.setStyle("-fx-text-fill: #cbd5e1;");

        Label stock = new Label("Stock: " + product.getStock());
        stock.setStyle("-fx-text-fill: #cbd5e1;");

        TextField countField = new TextField();
        countField.setPromptText("Count");
        countField.setMaxWidth(80);

        Button btnAddToCart = new Button("Add to Cart");
        btnAddToCart.setStyle(
            "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-font-weight: bold; " +
            "-fx-padding: 8 16; -fx-background-radius: 8;"
        );

        Label errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: #ef4444; -fx-font-weight: bold;");


        // cart validation
        btnAddToCart.setOnAction(e -> {
            String text = countField.getText().trim();

            // Validation: Must be filled
            if (text.isEmpty()) {
                errorLabel.setText("Count is required!");
                return;
            }

            // Validation: Must be numeric
            if (!text.matches("\\d+")) {
                errorLabel.setText("Count must be numeric!");
                return;
            }

            int qty = Integer.parseInt(text);

            // Validation: Must be >= 1
            if (qty < 1) {
                errorLabel.setText("Count must be at least 1!");
                return;
            }

            // Validation: Must be <= stock
            if (qty > product.getStock()) {
                errorLabel.setText("Maximum stock: " + product.getStock());
                return;
            }

            // Success
            errorLabel.setStyle("-fx-text-fill: #22c55e;");
            errorLabel.setText("Added to cart!");
        });

        VBox card = new VBox(8, name, price, stock, countField, btnAddToCart, errorLabel);
        card.setPadding(new Insets(18));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
            "-fx-background-color: #2d2d2d; -fx-background-radius: 12; -fx-padding: 15; " +
            "-fx-border-color: #3b82f6; -fx-border-width: 2; -fx-border-radius: 12;"
        );
        card.setPrefWidth(250);

        return card;
    }

    
    // inner class produk sementara
    private static class Product {
        private String name;
        private int price;
        private int stock;

        public Product(String name, int price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public String getName() { return name; }
        public int getPrice() { return price; }
        public int getStock() { return stock; }
    }
}
