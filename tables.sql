-- Create the schema for the inventory system manager
CREATE SCHEMA inventory_manager;

-- Set the search path to the inventory_manager schema
SET search_path TO inventory_manager;

-- Table for parts
CREATE TABLE part
(
    part_id SERIAL PRIMARY KEY,
    name    VARCHAR(255)   NOT NULL,
    inv     INT            NOT NULL,
    min     INT            NOT NULL,
    max     INT            NOT NULL,
    price   DECIMAL(10, 2) NOT NULL
);

-- Table for in-house parts
CREATE TABLE inhouse_part
(
    part_id    INT PRIMARY KEY,
    machine_id INT NOT NULL,
    FOREIGN KEY (part_id) REFERENCES part (part_id)
);

-- Table for outsourced parts
CREATE TABLE outsourced_part
(
    part_id     INT PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (part_id) REFERENCES part (part_id)
);

-- Table for products
CREATE TABLE product
(
    product_id SERIAL PRIMARY KEY,
    inv        INT            NOT NULL,
    min        INT            NOT NULL,
    max        INT            NOT NULL,
    name       VARCHAR(255)   NOT NULL,
    price      DECIMAL(10, 2) NOT NULL
);

-- Table to associate parts with products (many-to-many relationship)
CREATE TABLE product_part
(
    product_id INT NOT NULL,
    part_id    INT NOT NULL,
    PRIMARY KEY (product_id, part_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id),
    FOREIGN KEY (part_id) REFERENCES part (part_id)
);

-- Table for the inventory
CREATE TABLE inventory
(
    inventory_id SERIAL PRIMARY KEY
);

-- Table to associate products with the inventory (many-to-many relationship)
CREATE TABLE inventory_product
(
    inventory_id INT NOT NULL,
    product_id   INT NOT NULL,
    PRIMARY KEY (inventory_id, product_id),
    FOREIGN KEY (inventory_id) REFERENCES inventory (inventory_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

-- Table to associate parts with the inventory (many-to-many relationship)
CREATE TABLE inventory_part
(
    inventory_id INT NOT NULL,
    part_id      INT NOT NULL,
    PRIMARY KEY (inventory_id, part_id),
    FOREIGN KEY (inventory_id) REFERENCES inventory (inventory_id),
    FOREIGN KEY (part_id) REFERENCES part (part_id)
);
