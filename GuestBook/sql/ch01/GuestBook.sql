CREATE DATABASE Tutorial

USE Tutorial;

CREATE TABLE GuestBook(
VisitorNo Int PRIMARY KEY AUTO_INCREMENT,
VisitorName varchar(50),
Message varchar(100),
MessageDate varchar(40));

CREATE USER 'tutorial'@'localhost' IDENTIFIED BY 'tutorial';
GRANT ALL PRIVILEGES ON tutorial.* TO 'tutorial'@'localhost' identified by 'tutorial';