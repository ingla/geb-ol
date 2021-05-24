insert into Discipline (id, name, location, date, isCup)
values (1, 'Bowling', 'Bowling 1 Elverum', '2008-01-01 12:00:00+01:00', false);

insert into Participant (id, name)
values (1, 'Sigurd'),
(2, 'Ingvild');

insert into Result (disciplineId, participantId, place)
values (1, 1, 2),
(1, 2, 1);