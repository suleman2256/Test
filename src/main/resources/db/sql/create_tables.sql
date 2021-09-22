--changeset: Departments_sql
CREATE TABLE Departments (
                             id BIGINT PRIMARY KEY NOT NULL,
                             full_name VARCHAR (2048),
                             short_name VARCHAR (2048),
                             phone_number VARCHAR (2048)
);

ALTER TABLE Departments OWNER TO postgres;

--changeset: Person_sql
CREATE TABLE Persons (
                         id BIGINT PRIMARY KEY NOT NULL,
                         first_name VARCHAR (2048),
                         last_name VARCHAR (2048),
                         middle_name VARCHAR (2048),
                         date date,
                         month_salary numeric,
                         department_id BIGINT NOT NULL,
                         FOREIGN KEY(department_id) REFERENCES Departments(id)
);

ALTER TABLE Persons OWNER TO postgres;