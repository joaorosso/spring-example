create table usuarios(
    id serial primary key,
    username varchar(40) not null unique,
    password varchar(60) not null
);

INSERT INTO public.usuarios(
	username, password)
	VALUES ('admin', '$2a$12$6zwYfnvHPbIN1TdfK0/rH.oAD1A/ieP/D94AS6JW.reEA1tW1pP3S');