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
values (1, 'Sigurd'), (3, 'Andreas'), (4, 'Trine'), (5, 'Lars'),
(2, 'Ingvild'),(6, 'Lisa'),(7, 'Julie'),(8, 'Odin'),(9, 'Alexander B. R.'),(10, 'Alfjos'),(11, 'Brølløv'),(12, 'Lussi'),(13, 'Bjarne'),(14, 'Brofjøs'),(16, 'Niels'),(15,'Thomas'),(17, 'Idun');

insert into Result (disciplineId, participantId, place)
values (1, 1, 2), (1, 3, 3), (1, 4, 4), (1, 5, 5),
(1, 2, 1),(1, 6, 6),(1, 7, 7),(1, 8, 8),(1, 9, 9),(1, 10, 10),(1, 11, 11),(1, 12, 12),(1, 13, 13),(1, 14, 14),
(2, 1, 2), (2, 3, 3), (2, 4, 4), (2, 5, 5),
(2, 2, 1),(2, 6, 6),(2, 7, 7),(2, 8, 8),(2, 9, 9),(2, 10, 10),(2, 11, 11),(2, 12, 12),(2, 13, 13),(2, 14, 14),
(11, 1, 12), (11, 3, 3), (11, 4, 4), (11, 5, 5),
(11, 2, 2),(11, 6, 6),(11, 7, 7),(11, 8, 8),(11, 9, 9),(11, 10, 10),(11, 11, 11),(11, 12, 1),(11, 13, 13),(11, 14, 14),(11,16,15),(11,15,16);