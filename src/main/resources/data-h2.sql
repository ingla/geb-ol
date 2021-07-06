insert into Discipline (id, name, location, date, isCup)
values  (1, 'Frisbeegolf', 'ELVIS', '2021-08-05 16:30:00+01:00', false),
        (2, 'Bowling', 'Bowling 1 Elverum', '2021-08-05 18:00:00+01:00', false),
        (3, 'Petanque', 'EUS', '2021-08-06 13:00:00+01:00', true),
        (4, 'Tennis', 'Terningen Arena', '2021-08-05 13:00:00+01:00', true),
        (7, 'Idealtid', 'EUS', '2021-08-06 13:00:00+01:00', false),
        (5, 'Basket', 'Terningen Arena', '2021-08-05 13:00:00+01:00', true),
        (8, 'Bordtennis', 'Ydalir Skole', '2021-08-06 17:00:00+01:00', true),
        (9, 'Luftgevær', 'Hos Tom', '2021-08-07 14:00:00+01:00', false),
        (10, 'Dart', 'Hos Tom', '2021-08-07 17:0:00+01:00', true),
        (12, 'Badminton', 'Hos Tom', '2021-08-07 14:00:00+01:00', true),
        (11, 'Beerpong', 'Hos Tom', '2021-08-07 17:00:00+01:00', true),
        (6, 'Tverra', 'EUS', '2021-08-06 15:30:00+01:00', false);

insert into Participant (id, name)
values (1, 'Sigurd'), (3, 'Stian H.'), (4, 'Peder'), (5, 'Lars'),
(2, 'Ingvild'),(6, 'Håkon'),(7, 'Alexander'),(8, 'Øyvind L.'),(9, 'Sindre'),(10, 'Simen K.'),(11, 'Simen J. L.'),(12, 'Tom'),(13, 'Kaare'),(14, 'Herman'),(16, 'Vetle'),(15,'Åsmund'),(17, 'Ina'),(18, 'Regine'), (19, 'Ingrid');

insert into Result (disciplineId, participantId, place)
values (1, 1, 2), (1, 3, 3), (1, 4, 4), (1, 5, 5),
(1, 2, 1),(1, 6, 6),(1, 7, 7),(1, 8, 8),(1, 9, 9),(1, 10, 10),(1, 11, 11),(1, 12, 12),(1, 13, 13),(1, 14, 14),
(2, 1, 2), (2, 3, 3), (2, 4, 4), (2, 5, 5),
(2, 2, 1),(2, 6, 6),(2, 7, 7),(2, 8, 8),(2, 9, 9),(2, 10, 10),(2, 11, 11),(2, 12, 12),(2, 13, 13),(2, 14, 14),
(11, 1, 12), (11, 3, 3), (11, 4, 4), (11, 5, 5),
(11, 2, 2),(11, 6, 6),(11, 7, 7),(11, 8, 8),(11, 9, 9),(11, 10, 10),(11, 11, 11),(11, 12, 1),(11, 13, 13),(11, 14, 14),(11,16,15),(11,15,16),
(8, 11, 1), (8, 3, 2), (8, 9, 3), (8, 2, 4),
(8, 4, 5), (8, 8, 6), (8, 1, 7), (8, 6, 8),
(8, 7, 9);

insert into LiveResult (disciplineId, bracketLevel, place, participantId, score)
values
(8, 0, 0, 11, 2, false), (8, 0, 1, 9, 2, false), (8, 0, 2, 2, 1, false), (8, 0, 3, 3, 1, false),
(8, 1, 0, 9, 0, false), (8, 1, 1, 11, 2, false), (8, 1, 2, 2, 1, false), (8, 1, 3, 3, 2, false),
(8, 2, 0, 1, 1, false), (8, 2, 1, 9, 2, false), (8, 2, 2, 11, 2, false), (8, 2, 3, 4, 0, false),
(8, 2, 4, 2, 2, false), (8, 2, 5, 6, 1, false), (8, 2, 6, 8, 0, false), (8, 2, 7, 3, 2, false),
(8, 3, 0, 7, 1, false), (8, 3, 1, 1, 2, false);

