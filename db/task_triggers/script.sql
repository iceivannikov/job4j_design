create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

select * from products;

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id in (select id from inserted);
        return null;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 15, 150);
insert into products (name, producer, count, price)
VALUES ('product_4', 'producer_4', 20, 200);
insert into products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 30, 300);

select * from products;

create
or replace function tax_row()
    returns trigger as
$$
    BEGIN
        NEW.price := NEW.price * 1.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_row
    before insert
    on products
    for each row
    execute procedure tax_row();

insert into products (name, producer, count, price)
VALUES ('product_6', 'producer_6', 60, 600);

select * from products;

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function insert_price()
    returns trigger as
$$
    BEGIN
		insert into history_of_price (name, price, date)
		VALUES (NEW.name, NEW.price, current_timestamp);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger insert_trigger_row
    after insert
    on products
    for each row
    execute procedure insert_price();

insert into products (name, producer, count, price)
VALUES ('product_7', 'producer_7', 70, 700);

select * from products;
select * from history_of_price;
