create table stat_items
(
	id bigint not null
		constraint stat_items_pkey
			primary key,
	date_time timestamp,
	measurement integer,
	value double precision
);

alter table stat_items owner to postgres;

create table users
(
	id bigint not null
		constraint users_pkey
			primary key,
	email varchar(255),
	enabled boolean not null,
	first_name varchar(255),
	last_name varchar(255),
	password varchar(255),
	token_expired boolean not null
);

alter table users owner to postgres;


