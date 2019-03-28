--liquibase formatted sql

--changeset allanmleite:1

CREATE TABLE topic (
  subject varchar(100) NOT NULL,  
  id  SERIAL PRIMARY KEY NOT NULL
)

--changeset allanmleite:2

CREATE TABLE session (
  id SERIAL PRIMARY KEY NOT NULL,
  topic_id INTEGER REFERENCES topic(id),
  duration_in_minutes INTEGER,
  date_added timestamp NULL
);

--changeset allanmleite:3

CREATE TABLE associated (
  id SERIAL PRIMARY KEY NOT NULL,
  cpf varchar(15) NOT NULL
);

--changeset allanmleite:4

CREATE TABLE vote (
  id SERIAL PRIMARY KEY NOT NULL,
  session_id INTEGER NOT NULL REFERENCES session(id),
  associated_id INTEGER NOT NULL REFERENCES associated(id),
  vote varchar(20) NOT NULL
);

--changeset allanmleite:5

INSERT INTO associated(cpf) VALUES ('01234567890');
INSERT INTO associated(cpf) VALUES ('22445124018');
INSERT INTO associated(cpf) VALUES ('84986145037');
INSERT INTO associated(cpf) VALUES ('45216076085');
INSERT INTO associated(cpf) VALUES ('65288150036');
INSERT INTO associated(cpf) VALUES ('71872379010');
INSERT INTO associated(cpf) VALUES ('71072666081');
