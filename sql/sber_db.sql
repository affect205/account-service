CREATE TABLE account (
id serial PRIMARY KEY,
num CHARACTER VARYING unique NOT NULL,
balance decimal NOT NULL DEFAULT 0.0
);

INSERT INTO account(num, balance)
VALUES
('4214541869164421', 22260),
('4218320071804904', 35140),
('4216540268327624', 76280),
('4214268271334816', 44750);

CREATE TABLE operation_type (
id serial PRIMARY KEY,
name CHARACTER VARYING unique NOT NULL
);

INSERT INTO operation_type(name)
VALUES('TRANSFER');

CREATE TABLE operation (
id serial PRIMARY KEY,
operation_type INTEGER REFERENCES operation_type(id),
sender_id INTEGER REFERENCES account(id),
recipient_id INTEGER REFERENCES account(id),
operation_date TIMESTAMP without time zone NOT NULL,
amount decimal NOT NULL DEFAULT 0.0,
description CHARACTER VARYING
);

INSERT INTO operation(operation_type, sender_id, recipient_id, operation_date, amount)
VALUES
(1, 1, 2, now(), 970.0),
(1, 2, 1, now(), 475.0);