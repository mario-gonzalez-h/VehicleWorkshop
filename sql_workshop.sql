CREATE DATABASE dealership;

USE dealership;

CREATE TABLE dealerships (
    DealershipID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50),
    Address VARCHAR(50),
    Phone VARCHAR(12)
);

CREATE TABLE vehicles (
    Vin INT PRIMARY KEY CHECK (Vin BETWEEN 00000 AND 99999),
    Year INT,
    Make VARCHAR(50),
    Model VARCHAR(50),
    VehicleType VARCHAR(50),
    Color VARCHAR(30),
    Odometer INT CHECK (Odometer BETWEEN 000000 AND 999999),
    Price DOUBLE,
    SalesTaxAmount DOUBLE,
    RecordingFee DOUBLE,
    ProcessingFee DOUBLE,
    Finance BOOLEAN,
    DealershipID INT,
    FOREIGN KEY (DealershipID) REFERENCES dealerships(DealershipID)
);

CREATE TABLE inventory (
    Vin INT CHECK (Vin BETWEEN 10000 AND 99999),
    DealershipID INT,
    PRIMARY KEY (Vin, DealershipID),
    FOREIGN KEY (Vin) REFERENCES vehicles(Vin),
    FOREIGN KEY (DealershipID) REFERENCES dealerships(DealershipID)
);

CREATE TABLE customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50),
    Address VARCHAR(50),
    Phone VARCHAR(12),
    Email VARCHAR(50)
);

CREATE TABLE contracts (
    ContractID INT AUTO_INCREMENT PRIMARY KEY,
    DateOfContract DATE,
    CustomerName VARCHAR(50),
    CustomerEmail VARCHAR(50),
    Vin INT CHECK (Vin BETWEEN 10000 AND 99999),
    Year INT,
    Make VARCHAR(50),
    Model VARCHAR(50),
    VehicleType VARCHAR(50),
    Color VARCHAR(30),
    Odometer INT CHECK (Odometer BETWEEN 000000 AND 999999),
    Price DOUBLE,
    SalesTaxAmount DOUBLE,
    RecordingFee DOUBLE,
    ProcessingFee DOUBLE,
    Finance BOOLEAN,
    TotalPrice DOUBLE,
    VehicleSold BOOLEAN,
    MonthlyPayment DOUBLE,
    FOREIGN KEY (Vin) REFERENCES vehicles(Vin)
);

CREATE TABLE lease_contracts (
    LeaseContractID INT AUTO_INCREMENT PRIMARY KEY,
    ContractID INT,
    ExpectedEndingValue DOUBLE,
    LeaseFee DOUBLE,
    FOREIGN KEY (ContractID) REFERENCES contracts(ContractID)
);

# Insert data into dealerships table
INSERT INTO dealerships (Name, Address, Phone) 
VALUES
('Auto World', '123 Elm St', '123-456-7890'),
('Car Town', '456 Oak St', '234-567-8901'),
('Motor City', '789 Pine St', '345-678-9012');

# Insert data into vehicles table
INSERT INTO vehicles (Vin, Year, Make, Model, VehicleType, Color, Odometer, Price, SalesTaxAmount, RecordingFee, ProcessingFee, Finance, DealershipID) 
VALUES
(12345, 2020, 'Toyota', 'Camry', 'Sedan', 'Red', 015000, 20000.00, 1000.00, 100.00, 495.00, TRUE, 1),
(56789, 2019, 'Honda', 'Civic', 'Sedan', 'Blue', 020000, 18000.00, 900.00, 100.00, 495.00, FALSE, 1),
(91012, 2021, 'Ford', 'F-150', 'Truck', 'Black', 005000, 35000.00, 1750.00, 100.00, 495.00, TRUE, 2),
(11213, 2018, 'Chevrolet', 'Malibu', 'Sedan', 'White', 030000, 16000.00, 800.00, 100.00, 295.00, FALSE, 2),
(31415, 2022, 'Tesla', 'Model 3', 'Sedan', 'Silver', 001000, 45000.00, 2250.00, 100.00, 495.00, TRUE, 3),
(54321, 2022, 'Ford', 'Mustang', 'Coupe', 'Red', 000500, 55000.00, 2750.00, 100.00, 495.00, TRUE, 3);

# Insert data into inventory table
INSERT INTO inventory (Vin, DealershipID) 
VALUES
(12345, 1),
(56789, 1),
(91012, 2),
(11213, 2),
(31415, 3),
(54321, 3);

# Insert data into customers table
INSERT INTO customers (Name, Address, Phone, Email) 
VALUES
('John Doe', '101 Main St', '555-123-4567', 'john.doe@example.com'),
('Jane Smith', '202 Maple St', '555-234-5678', 'jane.smith@example.com'),
('Jim Brown', '303 Birch St', '555-345-6789', 'jim.brown@example.com');

# Insert data into contracts table
INSERT INTO contracts (DateOfContract, CustomerName, CustomerEmail, Vin, Year, Make, Model, VehicleType, Color, Odometer, Price, SalesTaxAmount, RecordingFee, ProcessingFee, Finance, TotalPrice, VehicleSold, MonthlyPayment) 
VALUES
('2024-01-15', 'John Doe', 'john.doe@example.com', 12345, 2020, 'Toyota', 'Camry', 'Sedan', 'Red', 015000, 20000.00, 1000.00, 100.00, 495.00, TRUE, 22000.00, TRUE, 500.00),
('2024-02-20', 'Jane Smith', 'jane.smith@example.com', 56789, 2019, 'Honda', 'Civic', 'Sedan', 'Blue', 020000, 18000.00, 900.00, 100.00, 495.00, FALSE, 19800.00, TRUE, 0.00);

# Insert data into lease_contracts table
INSERT INTO lease_contracts (ContractID, ExpectedEndingValue, LeaseFee) 
VALUES
(1, 25000.00, 500.00),
(2, 30000.00, 600.00);

# 1. Get all dealerships
SELECT *
FROM dealerships;

# 2. Find all vehicles for a specific dealership
SELECT *
FROM vehicles
WHERE DealershipID = 1;

# 3. Find a car by VIN
SELECT *
FROM vehicles
WHERE Vin = 12345;

# 4. Find the dealership where a certain car is located, by VIN
SELECT *
FROM dealerships
JOIN inventory ON dealerships.DealershipID = inventory.DealershipID
WHERE inventory.Vin = 12345;

# 5. Find all Dealerships that have a certain car type (e.g., Red Ford Mustang)
SELECT DISTINCT *
FROM dealerships
JOIN vehicles ON dealerships.DealershipID = vehicles.DealershipID
WHERE vehicles.Make = 'Ford' AND vehicles.Model = 'Mustang' AND vehicles.Color = 'Red';

# 6. Get all sales information for a specific dealer for a specific date range
SELECT *
FROM contracts
JOIN vehicles ON contracts.Vin = vehicles.Vin
WHERE vehicles.DealershipID = 1
AND contracts.DateOfContract BETWEEN '2024-01-01' AND '2024-12-31';
