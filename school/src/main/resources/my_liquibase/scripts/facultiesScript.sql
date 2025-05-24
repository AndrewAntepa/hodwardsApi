-- liquibase formatted sql

-- changeset andrew:1
CREATE TABLE facultyLog (
    id serial,
    name TEXT,
    color TEXT
)

-- changeset andrew:2
CREATE INDEX idx_faculties_name_color ON faculty(name, color);