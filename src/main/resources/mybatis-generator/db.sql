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

CREATE TABLE t_role (
  ID SERIAL primary key NOT NULL,
  ROLE_NAME varchar(32)  NOT NULL UNIQUE,
  ROLE_NAME_LOCAL varchar(32)  NOT NULL UNIQUE,
  DESCRIPTION varchar(255) ,
  GEN_TIME timestamp(6)
)
;

CREATE TABLE t_department (
  ID SERIAL primary key NOT NULL,
  DEPARTMENT_NAME varchar(32)  NOT NULL UNIQUE,
  DESCRIPTION varchar(255) ,
  PID int4 not null
)
;

CREATE TABLE t_position (
  ID SERIAL primary key NOT NULL,
  POSITION_NAME varchar(32)  NOT NULL,
  DESCRIPTION varchar(255) ,
  DEPARTMENT_ID int4 not null,
  PID int4 not null
)
;

CREATE TABLE t_ref_user_position (
  USER_ID int4 NOT NULL,
  POSITION_ID int4 NOT NULL
)
;

CREATE TABLE t_ref_user_role (
  USER_ID int4 NOT NULL,
  ROLE_ID int4 NOT NULL
)
;

CREATE TABLE t_privilege (
  ID SERIAL primary key NOT NULL,
  PRI_TYPE int4 NOT NULL
)
;

CREATE TABLE t_ref_privilege_role (
  PRI_ID int4 NOT NULL,
  ROLE_ID int4 NOT NULL
)
;

CREATE TABLE t_resource (
  ID SERIAL primary key NOT NULL,
  URL varchar(100)  NOT NULL UNIQUE,
  NAME varchar(50),
  TAG varchar(30) NOT NULL UNIQUE,
  PID int4 NOT NULL
)
;

CREATE TABLE t_ref_privilege_resource (
  PRI_ID int4 NOT NULL,
  RES_ID int4 NOT NULL
)
;

CREATE TABLE t_pac (
  PAC varchar(32) primary key NOT NULL,
  NAME varchar(32)  NOT NULL,
  PARENT varchar(32) NOT NULL
)
;

CREATE TABLE t_ref_user_pac (
 USER_ID int4 NOT NULL UNIQUE,
 PAC varchar(32) NOT NULL UNIQUE
)
;

CREATE TABLE t_ref_privilege_page (
  PRI_ID int4 NOT NULL,
  PAGE_ID int4 NOT NULL
)
;

CREATE TABLE t_page (
  ID SERIAL primary key NOT NULL,
  URL varchar(100) NOT NULL UNIQUE,
  PAGE_NAME varchar(100) NOT NULL,
  COMPONENT varchar(100),
  SORT int4 NOT NULL,
  DESCRIPTION varchar(255) ,
  PID int4 NOT NULL,
  MENU_TYPE int4,
  ICON varchar(100)
)
;