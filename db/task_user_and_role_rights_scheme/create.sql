create table roles(
	id integer generated by default as identity primary key,
	role varchar(255) not null
);

create table users(
	id integer generated by default as identity primary key,
	name varchar(255) not null,
	role_id integer not null,
	foreign key (role_id) REFERENCES roles(id)
);

create table rules(
	id integer generated by default as identity primary key,
	rule text not null
);

create table roles_rules(
	id integer generated by default as identity primary key,
	role_id integer not null,
	rule_id integer not null,
	foreign key (role_id) REFERENCES roles(id),
	foreign key (rule_id) REFERENCES rules(id)
);

create table categories(
	id integer generated by default as identity primary key,
	category varchar(255) not null
);

create table states(
	id integer generated by default as identity primary key,
	state varchar(255) not null
);

create table items(
	id integer generated by default as identity primary key,
	item varchar(255) not null,
	user_id integer not null,
	category_id integer not null,
	state_id integer not null,
	foreign key (user_id) REFERENCES users(id),
	foreign key (category_id) REFERENCES categories(id),
	foreign key (state_id) REFERENCES states(id)
);

create table comments(
	id integer generated by default as identity primary key,
	comment text not null,
	item_id integer not null,
	foreign key (item_id) REFERENCES items(id)
);

create table attachs(
	id integer generated by default as identity primary key,
	attach varchar(255) not null,
	file_data BYTEA not null,
	item_id integer not null,
	foreign key (item_id) REFERENCES items(id)
);