create table if not exists Discipline (
id identity,
name varchar(50) not null,
location varchar(50) not null,
date timestamp not null,
isCup boolean not null
);

create table if not exists Participant (
id identity,
name varchar(50) not null unique
);

create table if not exists Result (
disciplineId long not null,
participantId long not null,
place int not null
)