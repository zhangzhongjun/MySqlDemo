/*
 *	在mysql命令行中，执行
 *	source 路径 
 * 注意路径后面不加分号
 *
*/
DROP DATABASE IF EXISTS QWSS;

CREATE DATABASE QWSS;

USE QWSS;

DROP TABLE IF EXISTS test;

CREATE TABLE test (
  id  INT(11)     NOT NULL AUTO_INCREMENT,
  str VARCHAR(11) NOT NULL,
  i   INT         NOT NULL,
  obj BLOB not NULL ,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE test_obj (
obj BLOB not NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- insert into unit(id,unit_name) values(1,'西安电子科技大学');
-- insert into unit(id,unit_name) values(2,'西北工业大学');
