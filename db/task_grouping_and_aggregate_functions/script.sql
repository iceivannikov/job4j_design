create table devices(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people(
    id   serial primary key,
    name varchar(255)
);

create table devices_people(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('iPhone 13', 999.99);
insert into devices(name, price) values ('Samsung Galaxy S21', 799.99);
insert into devices(name, price) values ('Google Pixel 6', 599.99);
insert into devices(name, price) values ('OnePlus 9', 729.99);
insert into devices(name, price) values ('Sony Xperia 5 III', 949.99);
insert into devices(name, price) values ('Huawei P40 Pro', 899.99);
insert into devices(name, price) values ('Xiaomi Mi 11', 749.99);
insert into devices(name, price) values ('Oppo Find X3 Pro', 1099.99);
insert into devices(name, price) values ('Nokia 8.3 5G', 699.99);
insert into devices(name, price) values ('Motorola Edge+', 999.99);
insert into devices(name, price) values ('Asus ROG Phone 5', 999.99);
insert into devices(name, price) values ('LG Wing', 949.99);
insert into devices(name, price) values ('Realme GT', 599.99);
insert into devices(name, price) values ('ZTE Axon 30', 499.99);
insert into devices(name, price) values ('Vivo X60 Pro', 799.99);

insert into people (name) values ('Алексей');
insert into people (name) values ('Борис');
insert into people (name) values ('Виктор');
insert into people (name) values ('Галина');
insert into people (name) values ('Дмитрий');
insert into people (name) values ('Екатерина');
insert into people (name) values ('Зоя');
insert into people (name) values ('Иван');
insert into people (name) values ('Мария');
insert into people (name) values ('Николай');

insert into devices_people (device_id, people_id) values (32, 21);
insert into devices_people (device_id, people_id) values (33, 21);
insert into devices_people (device_id, people_id) values (34, 22);
insert into devices_people (device_id, people_id) values (35, 22);
insert into devices_people (device_id, people_id) values (36, 23);
insert into devices_people (device_id, people_id) values (37, 23);
insert into devices_people (device_id, people_id) values (38, 24);
insert into devices_people (device_id, people_id) values (39, 24);
insert into devices_people (device_id, people_id) values (40, 25);
insert into devices_people (device_id, people_id) values (41, 26);
insert into devices_people (device_id, people_id) values (42, 27);
insert into devices_people (device_id, people_id) values (43, 28);
insert into devices_people (device_id, people_id) values (44, 29);
insert into devices_people (device_id, people_id) values (45, 30);
insert into devices_people (device_id, people_id) values (46, 30);

select avg(price) from devices;

select p.name, avg(d.price)
from people p
join devices_people dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people p
join devices_people dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 700;