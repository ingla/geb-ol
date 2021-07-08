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
values (1, 'Tsjekkia'), (3, 'Italia'), (4, 'Ukraina'), (5, 'Sverige'), (2, 'Spania'),(6, 'Sveits'),(7, 'Nederland'),(8, 'Belgia'),(9, 'Danmark'),(10, 'Portugal'),
(11, 'England'),(12, 'Wales'),(13, 'Tyskalnd'),(14, 'Kroatia'),(16, 'Tyrkia'),(15,'Finland'),(17, 'Østerrike'),(18, 'Frankrike'), (19, 'Russland'), (20, 'N. Makedonia'),
(21, 'Skottland'), (22, 'Slovakia'), (23, 'Polen'), (24,'Ungarn');

insert into Result (disciplineId, participantId, place)
values (1, 1, 2), (1, 3, 3), (1, 4, 4), (1, 5, 5),
(1, 2, 1),(1, 6, 6),(1, 7, 7),(1, 8, 8),(1, 9, 9),(1, 10, 10),(1, 11, 11),(1, 12, 12),(1, 13, 13),(1, 14, 14),
(2, 1, 2), (2, 3, 3), (2, 4, 4), (2, 5, 5),
(2, 2, 1),(2, 6, 6),(2, 7, 7),(2, 8, 8),(2, 9, 9),(2, 10, 10),(2, 11, 11),(2, 12, 12),(2, 13, 13),(2, 14, 14),
(11, 1, 12), (11, 3, 3), (11, 4, 4), (11, 5, 5),
(11, 2, 2),(11, 6, 6),(11, 7, 7),(11, 8, 8),(11, 9, 9),(11, 10, 10),(11, 11, 11),(11, 12, 1),(11, 13, 13),(11, 14, 14),(11,16,15),(11,15,16);

insert into LiveResult (disciplineId, bracketLevel, place, participantId, score, knockedOut)
values
(8, 0, 0, -1, -1, false), (8, 0, 1, -1, -1, false), (8, 0, 2, -1, -1, false), (8, 0, 3, 3, -1, false),

(8, 1, 0, 9, -1, false), (8, 1, 1, 11, -1, false), (8, 1, 2, 2, 1, true), (8, 1, 3, 3, 1, false),

(8, 2, 1, 1, 1, true), (8, 2, 0, 9, 2, false), (8, 2, 2, 11, 4, false), (8, 2, 3, 4, 0, true),
(8, 2, 4, 2, 1, false), (8, 2, 5, 6, 1, true), (8, 2, 7, 8, 1, true), (8, 2, 6, 3, 2, false),

(8, 3, 2, 7, 0, true), (8, 3, 3, 1, 2, false),(8, 3, 1, 12, 0, true), (8, 3, 0, 9, 4, false),
(8, 3, 4, 13, 0, true), (8, 3, 5, 11, 2, false),(8, 3, 6, 4, 2, false), (8, 3, 7, 5, 1, true),
(8, 3, 8, 2, 5, false), (8, 3, 9, 14, 3, true),(8, 3, 10, 6, 3, false), (8, 3, 11, 18, 3, true),
(8, 3, 13, 3, 2, false), (8, 3, 14, 10, 0, true),(8, 3, 12, 17, 1, true), (8, 3, 15, 8, 1, false),

(8, 4, 26, 16, 0, true), (8, 4, 27, 3, 3, false),(8, 4, 30, 15, 0, true), (8, 4, 31, 8, 2, false),
(8, 4, 0, 19, 1, true), (8, 4, 1, 9, 4, false), (8, 4, 4, 20, 0, true), (8, 4, 5, 7, 3, false),
(8, 4, 10, 11, 0, false), (8, 4, 11, 21, 0, true),(8, 4, 15, 22, 0, true), (8, 4, 14, 5, 0, false),
(8, 4, 16, 2, 1, false), (8, 4, 17, 23, 1, true),(8, 4, 22, 18, 1, false), (8, 4, 23, 24, 1, true);

