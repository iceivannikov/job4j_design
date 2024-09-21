CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    producer VARCHAR(100),
    count INT,
    price NUMERIC
);

INSERT INTO products (name, producer, count, price) VALUES
('Product A', 'Producer A', 100, 15.00),
('Product B', 'Producer B', 50, 25.00),
('Product C', 'Producer C', 200, 30.00);

BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT * FROM products WHERE id = 6;
UPDATE products SET price = price + 10 WHERE id = 6;
COMMIT;

BEGIN;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT * FROM products WHERE id = 6;
UPDATE products SET price = price - 10 WHERE id = 6;
COMMIT;