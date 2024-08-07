create table clients(
id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(255) not null
);

create table orders(
id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
order_date date not null,
client_id integer not null,
foreign key (client_id) REFERENCES clients(id)
);