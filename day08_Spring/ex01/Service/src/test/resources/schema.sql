create schema if not exists Service;
drop table if exists Service.users CASCADE;

create table if not exists Service.users (
    id SERIAL,
    email varchar(128) NOT NULL,
    PRIMARY KEY (id)
);
