CREATE DATABASE IF NOT EXISTS teacher;
USE teacher;

CREATE TABLE IF NOT EXISTS teacher
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    name        varchar(255) NOT NULL,
    last_name   varchar(255) NOT NULL,
    title       varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS subject
(
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    code                    varchar(255) NOT NULL,
    name                    varchar(255) NOT NULL,
    description             varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS teacher_subject
(
    teacher_id                      BIGINT NOT NULL,
    subject_id                      BIGINT NOT NULL,
    PRIMARY KEY (teacher_id, subject_id),

    CONSTRAINT `FK_TEACHER_SUBJECT_TEACHER` FOREIGN KEY (teacher_id)
    REFERENCES teacher (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_TEACHER_SUBJECT_SUBJECT` FOREIGN KEY (`subject_id`)
    REFERENCES subject (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);