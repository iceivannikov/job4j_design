CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES
('Иван', 'Иванов', 28, 'Россия'),
('Ольга', 'Петрова', 34, 'Беларусь'),
('Алексей', 'Смирнов', 45, 'Россия'),
('Мария', 'Сидорова', 30, 'Украина'),
('Дмитрий', 'Кузнецов', 52, 'Казахстан'),
('Екатерина', 'Морозова', 27, 'Россия'),
('Сергей', 'Васильев', 40, 'Беларусь'),
('Анна', 'Кириллова', 22, 'Украина'),
('Николай', 'Григорьев', 35, 'Россия'),
('Елена', 'Соколова', 31, 'Казахстан');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id)
VALUES
(5000, 1),
(3000, 2),
(4500, 3),
(7000, 4),
(2500, 5),
(6000, 6),
(8000, 7),
(4000, 8),
(3500, 9),
(10000, 10);

select * from customers where id not in (select customer_id from orders);

