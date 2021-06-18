create table task (
	id serial,
	title varchar(50),
	description varchar(150),
	targetDate date,
	done boolean
);

insert into task(title, description, targetDate, done) values ('Clean up work desk', 'I need to clean my work desk, cause its really messy', '2021-06-25', true);