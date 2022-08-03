DROP TABLE employees IF EXISTS;

CREATE TABLE employees  (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	firstname VARCHAR(20),
	lastname VARCHAR(20)
);