
--changeset TestPerson_sql
CREATE TABLE Persons (
    id INT  NOT NULL,
    first_name VARCHAR (45),
    last_name VARCHAR (45),
    patronymic VARCHAR (45),
    date DATE,
    department_id BIGINT,
    PRIMARY KEY (ID)
);

--changeset TestDepartments_sql
CREATE TABLE Departments (
    id INT  NOT NULL,
    full_name VARCHAR (45),
    short_name VARCHAR (45),
    phone_number VARCHAR (45),
    PRIMARY KEY (ID)
)