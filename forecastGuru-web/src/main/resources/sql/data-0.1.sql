-- Roles:
insert into Role (name, description)
values ('General', 'Role with general permissions');
insert into Role (name, description)
values ('Administrator', 'Role with administration permissions');

-- Users:
-- SHA1(123123) = 601f1889667efaebb33b8c12572835da3f027f78
insert into BaseUser (userName, password, firstName, lastName)
values ('mheras', '601f1889667efaebb33b8c12572835da3f027f78', 'Martin', 'Heras');
insert into BaseUser (userName, password, firstName, lastName)
values ('fsiviero', '601f1889667efaebb33b8c12572835da3f027f78', 'Francisco', 'Siviero');
insert into BaseUser (userName, password, firstName, lastName)
values ('jbarreira', '601f1889667efaebb33b8c12572835da3f027f78', 'Juan', 'Barreira');
insert into BaseUser (userName, password, firstName, lastName)
values ('iluciani', '601f1889667efaebb33b8c12572835da3f027f78', 'Ignacio', 'Luciani');
insert into BaseUser (userName, password, firstName, lastName)
values ('hrajchert', '601f1889667efaebb33b8c12572835da3f027f78', 'Hernan', 'Rajchert');

-- Users in 'General' role:
insert into User_Role (user_id, role_id)
values (
	(select id from BaseUser where userName = 'mheras'),
	(select id from Role where name = 'General'));
insert into User_Role (user_id, role_id)
values (
	(select id from BaseUser where userName = 'fsiviero'),
	(select id from Role where name = 'General'));
insert into User_Role (user_id, role_id)
values (
	(select id from BaseUser where userName = 'hrajchert'),
	(select id from Role where name = 'General'));
insert into User_Role (user_id, role_id)
values (
	(select id from BaseUser where userName = 'jbarreira'),
	(select id from Role where name = 'General'));
insert into User_Role (user_id, role_id)
values (
	(select id from BaseUser where userName = 'iluciani'),
	(select id from Role where name = 'General'));
	
-- Users in 'Administrator' role:
insert into User_Role (user_id, role_id)
values (
	(select id from BaseUser where userName = 'mheras'),
	(select id from Role where name = 'Administrator'));