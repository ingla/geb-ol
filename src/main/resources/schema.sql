create table if not exists Discipline (
id bigint auto_increment primary key,
name varchar(50) not null,
location varchar(50) not null,
date timestamp not null,
isCup boolean not null
);

create table if not exists Participant (
id bigint auto_increment primary key,
name varchar(50) not null unique
);

create table if not exists Result (
disciplineId bigint not null,
participantId bigint not null,
place int not null
)