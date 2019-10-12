create user security with password 'wismap123';
create database security owner security;
grant all privileges on database security to security;

CREATE TABLE t_user (
  ID SERIAL primary key NOT NULL,
  LOGINNAME varchar(64)  NOT NULL UNIQUE,
  PASSWORD varchar(255)  NOT NULL,
  LOCALUSERNAME varchar(64)  NOT NULL,
  MOBILE varchar(32) UNIQUE,
  EMAIL varchar(64),
  GEN_TIME timestamp(6) NOT NULL,
  LAST_LOGIN_TIME timestamp(6),
  LOGIN_COUNT int4,
  STATUS int2 NOT NULL,
  IMG varchar(255)
)
;