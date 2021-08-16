CREATE DATABASE IF NOT EXISTS student;
USE student;

CREATE TABLE IF NOT EXISTS student
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    name        varchar(255) NOT NULL,
    last_name   varchar(255) NOT NULL,
    phone       integer  DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS domicile
(
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    street                  varchar(255) NOT NULL,
    street_num              integer  NOT NULL,
    department_num_floor    integer  DEFAULT NULL,
    department_num_room     integer  DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS student_domicile
(
    student_id                      BIGINT NOT NULL,
    domicile_id                     BIGINT NOT NULL,
    PRIMARY KEY (student_id, domicile_id),

    CONSTRAINT `FK_STUDENT_DOMICILE_STUDENT` FOREIGN KEY (student_id)
    REFERENCES student (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_STUDENT_DOMICILE_DOMICILE` FOREIGN KEY (`domicile_id`)
    REFERENCES domicile (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);