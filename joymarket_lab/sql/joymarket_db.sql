CREATE DATABASE joymarket_db;
USE joymarket_db;

CREATE TABLE MsUser (
    UserID CHAR(5) PRIMARY KEY,
    UserName VARCHAR(100),
    UserEmail VARCHAR(100),
    UserPassword VARCHAR(100),
    UserGender VARCHAR(20),
    UserAddress VARCHAR(255),
    UserPhone VARCHAR(20),
    UserBalance DOUBLE,
    UserRole VARCHAR(20)
);

INSERT INTO MsUser VALUES ('CS001', 'Budi Santoso', 'budi@gmail.com', '123456', 'Male', 'Jl. Sudirman No 1', '08123456789', 1000000, 'Customer');
INSERT INTO MsUser VALUES ('AD001', 'Admin Joy', 'admin@gmail.com', 'admin123', 'Female', 'Office Tower', '08111111111', 0, 'Admin');
INSERT INTO MsUser VALUES ('CR001', 'Kurir Cepat', 'courier@gmail.com', 'courier123', 'Male', 'Warehouse', '08222222222', 0, 'Courier');

CREATE TABLE MsProduct (
    ProductID CHAR(5) PRIMARY KEY,
    ProductName VARCHAR(100),
    ProductPrice DOUBLE,
    ProductStock INT
);

INSERT INTO MsProduct VALUES 
('PD001', 'Fresh Beef (1kg)', 120000, 50),
('PD002', 'Organic Apple (1kg)', 45000, 100),
('PD003', 'Salmon Fillet (500g)', 95000, 30),
('PD004', 'Milk UHT (1L)', 18000, 200),
('PD005', 'Whole Wheat Bread', 25000, 40);

CREATE TABLE MsPromo (
    PromoCode VARCHAR(50) PRIMARY KEY,
    DiscountAmount DOUBLE
);

INSERT INTO MsPromo VALUES ('HEMAT10000', 10000);
INSERT INTO MsPromo VALUES ('DISKON5000', 5000);

CREATE TABLE MsCart (
    UserID CHAR(5),
    ProductID CHAR(5),
    Quantity INT,
    PRIMARY KEY (UserID, ProductID),
    FOREIGN KEY (UserID) REFERENCES MsUser(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES MsProduct(ProductID) ON DELETE CASCADE
);

CREATE TABLE MsTransaction (
    TransactionID CHAR(5) PRIMARY KEY,
    UserID CHAR(5),
    CourierID CHAR(5),
    TransactionDate DATE,
    TotalPrice DOUBLE,
    Status VARCHAR(20),
    FOREIGN KEY (UserID) REFERENCES MsUser(UserID) ON DELETE CASCADE
);

CREATE TABLE MsTransactionDetail (
    TransactionID CHAR(5),
    ProductID CHAR(5),
    Quantity INT,
    PRIMARY KEY (TransactionID, ProductID),
    FOREIGN KEY (TransactionID) REFERENCES MsTransaction(TransactionID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES MsProduct(ProductID) ON DELETE CASCADE
);