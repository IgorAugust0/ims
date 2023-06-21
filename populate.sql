-- Insert sample data into the part table
INSERT INTO part (name, inv, min, max, price)
VALUES
    ('Part 1', 10, 2, 20, 10.99),
    ('Part 2', 5, 1, 10, 5.99),
    ('Part 3', 8, 2, 15, 8.49);

-- Insert sample data into the inhouse_part table
INSERT INTO inhouse_part (part_id, machine_id)
VALUES
    (1, 1001),
    (2, 1002);

-- Insert sample data into the outsourced_part table
INSERT INTO outsourced_part (part_id, company_name)
VALUES
    (3, 'Company A');

-- Insert sample data into the product table
INSERT INTO product (inv, min, max, name, price)
VALUES
    (5, 1, 10, 'Product 1', 29.99),
    (3, 1, 5, 'Product 2', 19.99);

-- Insert sample data into the product_part table
INSERT INTO product_part (product_id, part_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3);

-- Insert sample data into the inventory table
INSERT INTO inventory DEFAULT VALUES;

-- Insert sample data into the inventory_product table
INSERT INTO inventory_product (inventory_id, product_id)
VALUES
    (1, 1),
    (1, 2);

-- Insert sample data into the inventory_part table
INSERT INTO inventory_part (inventory_id, part_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3);