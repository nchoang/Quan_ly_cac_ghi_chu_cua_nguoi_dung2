CREATE DATABASE 'usermanagement';
USE usermanagement;

create table notes (
    id  int(3) NOT NULL AUTO_INCREMENT,
    title varchar(120) NOT NULL,
    content varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);

