
DROP TABLE IF EXISTS STUDENT;


CREATE TABLE STUDENT (
     id IDENTITY,
     first_name VARCHAR(200) NOT NULL,
     last_name VARCHAR(200) NOT NULL,
     email VARCHAR(200) DEFAULT NULL,
     last_modified TIMESTAMP default null
);


