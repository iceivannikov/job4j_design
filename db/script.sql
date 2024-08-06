CREATE TABLE users (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    age SMALLINT,
    birthday DATE
);

insert into users (name, age, birthday) values ('Viktor', 39, '1985-01-17');

select * from users;

update users set age = 40;

select * from users;

delete from users;

select * from users;