CREATE TABLE manufacturer (
	manufacturerID int NOT NULL AUTO_INCREMENT,
	manufacturerName varchar(30) NOT NULL,
	PRIMARY KEY (manufacturerID)
);

CREATE TABLE car (
	carID int NOT NULL AUTO_INCREMENT,
	manufacturerID int NOT NULL,
	carModel VARCHAR(30) NOT NULL,
	year int NOT NULL,
	color VARCHAR(30) NOT NULL,
	price decimal(7,2) NOT NULL,
	PRIMARY KEY (carID),
	FOREIGN KEY (manufacturerID) REFERENCES manufacturer(manufacturerID)
);

create table sec_user
(
	  userID BIGINT NOT NULL Primary Key AUTO_INCREMENT,
	  username VARCHAR(75) NOT NULL UNIQUE,
	  encryptedPassword VARCHAR(128) NOT NULL,
	  enabled BIT NOT NULL 
);

create table sec_role
(
  	roleID BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  	roleName VARCHAR(30) NOT NULL UNIQUE
);

create table user_role
(
  	userRoleID BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  	userID BIGINT NOT NULL,
  	roleID BIGINT NOT NULL
);

alter table user_role
  add constraint user_role_UK unique (userID, roleID);

alter table user_role
  add constraint user_role_FK1 foreign key (userID)
  references sec_user (userID);
 
alter table user_role
  add constraint user_role_FK2 foreign key (roleID)
  references sec_role (roleID);