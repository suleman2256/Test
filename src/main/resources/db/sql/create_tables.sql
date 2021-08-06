
--changeset Departments_sql
CREATE TABLE Departments (
                             id BIGINT PRIMARY KEY NOT NULL,
                             full_name VARCHAR (2048),
                             short_name VARCHAR (2048),
                             phone_number VARCHAR (2048)

);

--changeset Person_sql
CREATE TABLE Persons (
                         id BIGINT PRIMARY KEY NOT NULL,
                         first_name VARCHAR (2048),
                         last_name VARCHAR (2048),
                         patronymic VARCHAR (2048),
                         date DATE,
                         department_id BIGINT,
                         FOREIGN KEY (department_id) REFERENCES Departments(id)
);