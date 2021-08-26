CREATE TABLE IF NOT EXISTS rule (
  id serial primary key,
  name varchar(2000)
);

CREATE TABLE IF NOT EXISTS type (
  id serial primary key,
  name varchar(2000)
);

CREATE TABLE IF NOT EXISTS accident (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    type_id int references type(id)
);

CREATE TABLE IF NOT EXISTS accident_rule (
    accident_id int references accident(id),
    rule_id int references rule(id)
);


