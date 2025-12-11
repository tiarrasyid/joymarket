package model;

public class CartItem {
    private String productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private double totalPrice;

    public CartItem(String productId, String productName, double productPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.totalPrice = productPrice * quantity;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getProductPrice() { return productPrice; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
}