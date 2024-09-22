BEGIN;

SAVEPOINT sp1;
UPDATE products SET price = price + 10 WHERE id = 7;

SAVEPOINT sp2;
UPDATE products SET count = count - 5 WHERE id = 8;

SAVEPOINT sp3;
UPDATE products SET price = price - 5 WHERE id = 9;

ROLLBACK TO sp2;

ROLLBACK TO sp1;

COMMIT;
