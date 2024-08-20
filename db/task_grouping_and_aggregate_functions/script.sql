CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type_id INTEGER NOT NULL REFERENCES type(id),
    expired_date DATE NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

INSERT INTO type (name) VALUES 
('Electronics'),
('Furniture'),
('Clothing'),
('Food'),
('Books');

INSERT INTO product (name, type_id, expired_date, price) VALUES 
('Smartphone', 1, '2025-12-31', 699.99),
('Laptop', 1, '2026-01-31', 999.99),
('Table', 2, '2030-07-15', 149.99),
('Chair', 2, '2030-07-15', 89.99),
('Jacket', 3, '2025-12-31', 129.99),
('T-shirt', 3, '2024-06-30', 19.99),
('Bread', 4, '2024-08-19', 2.99),
('Milk', 4, '2024-08-22', 1.99),
('Apple', 4, '2024-08-25', 0.99),
('Chocolate', 4, '2025-01-01', 3.49),
('Novel', 5, '2030-12-31', 14.99),
('Cookbook', 5, '2030-12-31', 24.99),
('Desk', 2, '2030-07-15', 249.99),
('Monitor', 1, '2026-01-31', 199.99),
('Keyboard', 1, '2026-01-31', 49.99),
('Jeans', 3, '2025-12-31', 49.99),
('Sofa', 2, '2030-07-15', 599.99),
('Blender', 1, '2025-12-31', 79.99),
('Pasta', 4, '2024-12-31', 1.49),
('Cheese', 4, '2030-11-29', 1.35),
('Cereal', 4, '2025-02-28', 3.99);

select * from product 
join type on product.type_id = type.id
where type.name = 'Food';

select * from product
where name like '%oo%';

select * from product
where expired_date < '2024-08-20';

select t.name, p.price from product p
join type t on p.type_id = t.id
where p.price = (select max(price) from product);

select t.name as имя_типа, count(p) as количество from product p
join type t on p.type_id = t.id
group by t.name;

select t.name, p.name from product p
join type t on p.type_id = t.id
where t.name = 'Clothing' or t.name = 'Electronics';

select t.name as имя_типа, count(p) as количество from product p
join type t on p.type_id = t.id
group by t.name
having count(p) < 5;

select p.name, t.name from product p
join type t on p.type_id = t.id;
