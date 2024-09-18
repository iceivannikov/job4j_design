create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

create
or replace procedure delete_data(i_name varchar)
language 'plpgsql'
as $$
    BEGIN
        delete from products where name = i_name;
    END
$$;

call delete_data('product_2');

select * from products;

select f_insert_data('product_4', 'producer_4', 0, 50);
select f_insert_data('product_5', 'producer_5', 0, 32);

create or replace function f_delete_products_by_condition()
returns integer
language 'plpgsql'
as
$$
    declare
        remaining_count integer;
    begin
        delete from products
        where count = 0;

        select count(*) into remaining_count
        from products;

        return remaining_count;
    end;
$$;

select f_delete_products_by_condition();

select * from products;