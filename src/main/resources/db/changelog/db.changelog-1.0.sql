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