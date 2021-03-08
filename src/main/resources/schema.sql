create table if not exists Discipline (
id identity,
name varchar(50) not null,
location varchar(50) not null,
date timestamp not null,
isCup boolean not null
);