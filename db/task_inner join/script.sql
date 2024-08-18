create table Courses(
	id integer generated by default as identity primary key,
    name varchar(255) not null
);

create table Students(
	id integer generated by default as identity primary key,
    name varchar(255) not null,
	course_id integer not null,
	foreign key (course_id) references Courses(id)
);

insert into Courses(name) values ('С1');
insert into Courses(name) values ('С2');
insert into Courses(name) values ('С3');
insert into Courses(name) values ('С4');

insert into Students(name, course_id) values ('S1', 1);
insert into Students(name, course_id) values ('S2', 2);
insert into Students(name, course_id) values ('S3', 3);
insert into Students(name, course_id) values ('S4', 4);
insert into Students(name, course_id) values ('S5', 1);

select c.name as Курс, s.name as Студент
from Courses c
join Students s on s.course_id = c.id;

select s.name as Студент, c.name as Курс
from Students s
join Courses c on s.course_id = c.id;

select s.name as Студент, c.name as Курс
from Students s
join Courses c on s.course_id = c.id
where c.id = 1;