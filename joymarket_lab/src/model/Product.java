package model;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private int productStock;

    public Product(String productId, String productName, double productPrice, int productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getProductPrice() { return productPrice; }
    public int getProductStock() { return productStock; }
}
