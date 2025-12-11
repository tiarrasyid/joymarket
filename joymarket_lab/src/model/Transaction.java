package model;

public class Transaction {
    private String transactionId;
    private String itemName; // Digunakan untuk menyimpan Info (Total Harga / Tanggal)
    private String status;
    private String courierName; // Menyimpan ID/Nama Kurir

    public Transaction(String transactionId, String itemName, String status, String courierName) {
        this.transactionId = transactionId;
        this.itemName = itemName;
        this.status = status;
        this.courierName = courierName;
    }

    // --- GETTER (Wajib ada untuk TableView) ---
    public String getTransactionId() { return transactionId; }
    public String getItemName() { return itemName; }
    public String getStatus() { return status; }
    public String getCourierName() { return courierName; }
    
    // --- SETTER ---
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setStatus(String status) { this.status = status; }
    public void setCourierName(String courierName) { this.courierName = courierName; }
}