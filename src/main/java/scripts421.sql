DROP TABLE student;

DROP TABLE faculty;

CREATE TABLE IF NOT EXISTS faculty
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255),
    color VARCHAR(255),
    CONSTRAINT pk_faculty PRIMARY KEY (id),
    CONSTRAINT name_color_uq UNIQUE (name, color)
    );

CREATE TABLE IF NOT EXISTS student
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name       VARCHAR(255) UNIQUE                     NOT NULL,
    age        INTEGER CHECK ( age >= 16 ) DEFAULT 20  NOT NULL,
    faculty_id BIGINT,
    CONSTRAINT pk_student PRIMARY KEY (id)
    );

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_FACULTY FOREIGN KEY (faculty_id) REFERENCES faculty (id)
        ON DELETE SET NULL;

---test

INSERT INTO student (name)
VALUES ('A');

SELECT (SELECT age
        FROM student
        WHERE id = 1) = 20;