create table books(
id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
title varchar(255) not null
);

create table ISBN(
id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
ISBNCode varchar(255) not null,
book_id integer UNIQUE NOT NULL,
foreign key (book_id) REFERENCES books(id)
);