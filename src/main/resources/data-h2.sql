insert into Discipline (id, name, location, date, isCup)
values (1, 'Stein Saks Papir', 'Bowling 1 Elverum', '2008-01-01 12:00:00+01:00', true);

insert into Participant (id, name)
values (1, 'Sigurd'), (3, 'Andreas'), (4, 'Trine'), (5, 'Lars'),
(2, 'Ingvild'),(6, 'Lisa'),(7, 'Julie'),(8, 'Odin'),(9, 'Lases'),(10, 'Alfjos'),(11, 'Brølløv'),(12, 'Lussi'),(13, 'Bjarne'),(14, 'Brofjøs');

insert into Result (disciplineId, participantId, place)
values (1, 1, 2), (1, 3, 3), (1, 4, 4), (1, 5, 5),
(1, 2, 1),(1, 6, 6),(1, 7, 7),(1, 8, 8),(1, 9, 9),(1, 10, 10),(1, 11, 11),(1, 12, 12),(1, 13, 13),(1, 14, 14);