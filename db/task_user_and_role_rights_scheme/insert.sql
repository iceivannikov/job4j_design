insert into roles (role) values ('Admin');
insert into roles (role) values ('User');

insert into users (name, role_id) values ('Viktor', 1);
insert into users (name, role_id) values ('Alexey', 2);

insert into rules (rule) values ('Create Item');
insert into rules (rule) values ('Edit Item');
insert into rules (rule) values ('Delete Item');

insert into roles_rules (role_id, rule_id) values (1, 1);
insert into roles_rules (role_id, rule_id) values (1, 2);
insert into roles_rules (role_id, rule_id) values (1, 3);
insert into roles_rules (role_id, rule_id) values (2, 1);

insert into categories (category) values ('Bug');
insert into categories (category) values ('Request');

insert into states (state) values ('Open');
insert into states (state) values ('In Progress');
insert into states (state) values ('Closed');

insert into items (item, user_id, category_id, state_id) values ('Fix login bug', 1, 1, 1);

insert into comments (comment, item_id) values ('This bug is critical and needs immediate attention.', 1);

insert into attachs (attach, file_data, item_id)
	values ('screenshot.png', pg_read_binary_file('db/task_user_and_role_rights_scheme/screenshot.png'), 1);