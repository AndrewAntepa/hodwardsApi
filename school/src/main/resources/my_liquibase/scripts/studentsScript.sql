-- liquibase formatted sql

-- changeset andrew:1
CREATE TABLE studentLog (
    id serial,
    name TEXT
)

-- changeset andrew:2
CREATE INDEX idx_students_name ON student(name);