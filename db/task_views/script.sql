create table students (
    id   serial primary key,
    name varchar(50)
);

insert into students (name)
values ('Иван Иванов');
insert into students (name)
values ('Петр Петров');

create table authors (
    id   serial primary key,
    name varchar(50)
);

insert into authors (name)
values ('Александр Пушкин');
insert into authors (name)
values ('Николай Гоголь');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors (id)
);

insert into books (name, author_id)
values ('Евгений Онегин', 1);
insert into books (name, author_id)
values ('Капитанская дочка', 1);
insert into books (name, author_id)
values ('Дубровский', 1);
insert into books (name, author_id)
values ('Мертвые души', 2);
insert into books (name, author_id)
values ('Вий', 2);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (2, 2);

select
    authors.id AS author_id,
    authors.name AS author_name,
    books.id AS book_id,
    books.name AS book_name,
    students.id AS student_id,
    students.name AS student_name,
    orders.id AS order_id,
    orders.active AS order_status
from
    authors
join
    books ON authors.id = books.author_id
join
    orders ON books.id = orders.book_id
join
    students ON students.id = orders.student_id
where
    orders.active = TRUE
order by
    authors.name, books.name, students.name;

create view active_orders as
select
    authors.id AS author_id,
    authors.name AS author_name,
    books.id AS book_id,
    books.name AS book_name,
    students.id AS student_id,
    students.name AS student_name,
    orders.id AS order_id,
    orders.active AS order_status
from
    authors
join
    books ON authors.id = books.author_id
join
    orders ON books.id = orders.book_id
join
    students ON students.id = orders.student_id
where
    orders.active = TRUE;

select * from active_orders;

