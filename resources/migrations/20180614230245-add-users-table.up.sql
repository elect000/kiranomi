CREATE TABLE owners
(name VARCHAR(30) PRIMARY KEY,
 pass VARCHAR(300) NOT NULL);

CREATE TABLE plans
(name VARCHAR(30) NOT NULL,
 owner VARCHAR(30) NOT NULL REFERENCES owners(name),
 d timestamp NOT NULL,
 data JSON,
 PRIMARY KEY(name, owner));

CREATE TABLE guests
(name VARCHAR(30) NOT NULL PRIMARY KEY,
 plan_name VARCHAR(30) NOT NULL,
 owner VARCHAR(30) NOT NULL,
 data JSON,
 FOREIGN KEY (plan_name, owner) REFERENCES plans(name, owner));
