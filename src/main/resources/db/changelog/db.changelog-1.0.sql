--liquibase formatted sql

--changeset allanmleite:1
SELECT * FROM DATABASECHANGELOG;

--changeset allanmleite:2
CREATE TABLE IF NOT EXISTS pauta (
  subject varchar(100) NOT NULL,  
  id integer NOT NULL,  
  PRIMARY KEY (id)  
)

--changeset allanmleite:3
drop table pauta;

--changeset allanmleite:4
CREATE TABLE IF NOT EXISTS pauta (
  subject varchar(100) NOT NULL,  
  id  SERIAL PRIMARY KEY NOT NULL
)

--changeset allanmleite:5
CREATE TABLE sessao (
  id SERIAL PRIMARY KEY NOT NULL,
  pauta_id INTEGER REFERENCES pauta(id),
  duration_in_minutes INTEGER
);

--changeset allanmleite:6
CREATE TABLE IF NOT EXISTS topic (
  subject varchar(100) NOT NULL,  
  id  SERIAL PRIMARY KEY NOT NULL
)

--changeset allanmleite:7
CREATE TABLE session (
  id SERIAL PRIMARY KEY NOT NULL,
  topic_id INTEGER REFERENCES topic(id),
  duration_in_minutes INTEGER
);