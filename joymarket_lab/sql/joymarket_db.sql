CREATE DATABASE joymarket_db;
USE joymarket_db;

CREATE TABLE MsUser (
    UserID CHAR(5) PRIMARY KEY,
    UserName VARCHAR(100) NOT NULL,
    UserEmail VARCHAR(100) NOT NULL UNIQUE,
    UserPassword VARCHAR(100) NOT NULL,
    UserGender VARCHAR(10) NOT NULL,
    UserAddress VARCHAR(255) NOT NULL,
    UserPhone VARCHAR(15) NOT NULL,
    UserBalance DOUBLE DEFAULT 0,
    UserRole VARCHAR(20) NOT NULL 
);

CREATE TABLE MsProduct (
    ProductID CHAR(5) PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    ProductPrice DOUBLE NOT NULL,
    ProductStock INT NOT NULL
);

CREATE TABLE MsPromo (
    PromoCode VARCHAR(50) PRIMARY KEY,
    PromoDiscount DOUBLE NOT NULL
);

CREATE TABLE TrHeader (
    TransactionID CHAR(5) PRIMARY KEY,
    UserID CHAR(5),
    CourierID CHAR(5), 
    TransactionDate DATE,
    Status VARCHAR(20), 
    FOREIGN KEY (UserID) REFERENCES MsUser(UserID)
);

CREATE TABLE TrDetail (
    TransactionID CHAR(5),
    ProductID CHAR(5),
    Quantity INT,
    PRIMARY KEY (TransactionID, ProductID),
    FOREIGN KEY (TransactionID) REFERENCES TrHeader(TransactionID),
    FOREIGN KEY (ProductID) REFERENCES MsProduct(ProductID)
);

CREATE TABLE TrCart (
    UserID CHAR(5),
    ProductID CHAR(5),
    Quantity INT,
    PRIMARY KEY (UserID, ProductID),
    FOREIGN KEY (UserID) REFERENCES MsUser(UserID),
    FOREIGN KEY (ProductID) REFERENCES MsProduct(ProductID)
);

INSERT INTO MsUser VALUES ('AD001', 'Super Admin', 'admin@joymarket.com', 'admin123', 'Male', 'Office Tower', '08123456789', 0, 'Admin');
INSERT INTO MsUser VALUES ('CR001', 'Budi Courier', 'courier@joymarket.com', 'courier123', 'Male', 'Jakarta', '08987654321', 0, 'Courier');
INSERT INTO MsProduct VALUES ('PR001', 'Apple Fuji', 15000, 100), ('PR002', 'Beef Sirloin', 120000, 50), ('PR003', 'Fresh Milk', 25000, 200);
INSERT INTO MsPromo VALUES ('DISC10', 10000), ('HEMAT', 5000);