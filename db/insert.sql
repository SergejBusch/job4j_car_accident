insert into rule(name) values
    ('Rule. 1'),
    ('Rule. 2'),
    ('Rule. 3');

insert into accidenttype(name) values
    ('Two cars'),
    ('Car and man'),
    ('Car and bicycle');

insert into accident(name, text, address, type_id) values
    ('a', 'desc', 'a1', 1),
    ('b', 'desc', 'b2', 2),
    ('c', 'desc', 'c3', 3);

-- insert into accident_rule(accident_id, rules_id) values
--     (1, 1),
--     (1, 2),
--     (2, 3),
--     (3, 1),
--     (3, 3);