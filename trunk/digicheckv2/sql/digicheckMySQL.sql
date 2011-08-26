-- ----------------------------------------------------------------------
-- MySQL Migration Toolkit
-- SQL Create Script
-- ----------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `digicheck_dbo`
  CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `digicheck_dbo`;
-- -------------------------------------
-- Tables

DROP TABLE IF EXISTS `digicheck_dbo`.`ESTADO`;
CREATE TABLE `digicheck_dbo`.`ESTADO` (
  `EST_NOMBRE` VARCHAR(100) NOT NULL,
  `EST_CODIGO` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`EST_CODIGO`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`LOTE`;
CREATE TABLE `digicheck_dbo`.`LOTE` (
  `LOT_REFERENCIA` VARCHAR(100) NOT NULL,
  `LOT_IMPORTE` DECIMAL(18, 2) NOT NULL,
  `USU_LOGIN` VARCHAR(20) NOT NULL,
  `SUC_ID` INT(10) NOT NULL,
  `DIV_ID` INT(10) NOT NULL,
  `LOT_ID` INT(10) NOT NULL AUTO_INCREMENT,
  `LOT_DOCUMENTOS` INT(10) NOT NULL,
  `LOT_FECHA` DATETIME NOT NULL,
  `LOT_FECHA_ALTA` DATETIME NOT NULL,
  PRIMARY KEY (`LOT_ID`),
  CONSTRAINT `FK_SUC_LOT` FOREIGN KEY `FK_SUC_LOT` (`SUC_ID`)
    REFERENCES `digicheck_dbo`.`SUCURSAL` (`SUC_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USU_LOT` FOREIGN KEY `FK_USU_LOT` (`USU_LOGIN`)
    REFERENCES `digicheck_dbo`.`USUARIO` (`USU_LOGIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_DIV_LOT` FOREIGN KEY `FK_DIV_LOT` (`DIV_ID`)
    REFERENCES `digicheck_dbo`.`DIVISA` (`DIV_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`PERMISO`;
CREATE TABLE `digicheck_dbo`.`PERMISO` (
  `PER_ID` INT(10) NOT NULL,
  `PER_DESCRIPCION` VARCHAR(100) NOT NULL,
  `PET_CODE` CHAR(3) NOT NULL,
  PRIMARY KEY (`PER_ID`),
  CONSTRAINT `FK_PET_PER` FOREIGN KEY `FK_PET_PER` (`PET_CODE`)
    REFERENCES `digicheck_dbo`.`TIPO_PERMISO` (`PET_CODE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`SUCURSAL`;
CREATE TABLE `digicheck_dbo`.`SUCURSAL` (
  `SUC_ID` INT(10) NOT NULL AUTO_INCREMENT,
  `SUC_NOMBRE` VARCHAR(100) NOT NULL,
  `SUC_DIRECCION` VARCHAR(100) NULL,
  `SUC_COLONIA` VARCHAR(20) NULL,
  `SUC_CODIGO_POSTAL` VARCHAR(5) NULL,
  `SUC_CIUDAD` VARCHAR(100) NULL,
  `EST_CODIGO` VARCHAR(4) NULL,
  PRIMARY KEY (`SUC_ID`),
  CONSTRAINT `FK_EST_SUC` FOREIGN KEY `FK_EST_SUC` (`EST_CODIGO`)
    REFERENCES `digicheck_dbo`.`ESTADO` (`EST_CODIGO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`TIPO_DOCUMENTO`;
CREATE TABLE `digicheck_dbo`.`TIPO_DOCUMENTO` (
  `DOT_ID` CHAR(1) NOT NULL,
  `DOT_DESCRIPCION` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`DOT_ID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`TIPO_PERMISO`;
CREATE TABLE `digicheck_dbo`.`TIPO_PERMISO` (
  `PET_CODE` CHAR(3) NOT NULL,
  `PET_DESCRIPCION` VARCHAR(20) NULL,
  PRIMARY KEY (`PET_CODE`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`USUARIO`;
CREATE TABLE `digicheck_dbo`.`USUARIO` (
  `USU_LOGIN` VARCHAR(20) NOT NULL,
  `USU_NOMBRE` VARCHAR(100) NOT NULL,
  `USU_PASSWORD` VARCHAR(30) NOT NULL,
  `SUC_ID` INT(10) NULL,
  PRIMARY KEY (`USU_LOGIN`),
  CONSTRAINT `FK_SUC_USU` FOREIGN KEY `FK_SUC_USU` (`SUC_ID`)
    REFERENCES `digicheck_dbo`.`SUCURSAL` (`SUC_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`USUARIO_PERMISO`;
CREATE TABLE `digicheck_dbo`.`USUARIO_PERMISO` (
  `USU_LOGIN` VARCHAR(20) NOT NULL,
  `PER_ID` INT(10) NOT NULL,
  `USP_FECHA` DATETIME NOT NULL,
  PRIMARY KEY (`USU_LOGIN`, `PER_ID`),
  CONSTRAINT `FK_USU_USP` FOREIGN KEY `FK_USU_USP` (`USU_LOGIN`)
    REFERENCES `digicheck_dbo`.`USUARIO` (`USU_LOGIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PER_USP` FOREIGN KEY `FK_PER_USP` (`PER_ID`)
    REFERENCES `digicheck_dbo`.`PERMISO` (`PER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`CHEQUE`;
CREATE TABLE `digicheck_dbo`.`CHEQUE` (
  `CHQ_ID` INT(10) NOT NULL,
  `LOT_ID` INT(10) NOT NULL,
  `CHQ_ABBA` VARCHAR(50) NOT NULL,
  `CHQ_CUENTA` VARCHAR(20) NOT NULL,
  `CHQ_IMPORTE` DECIMAL(18, 2) NOT NULL,
  PRIMARY KEY (`CHQ_ID`),
  CONSTRAINT `FK_LOT_ID_CHQ` FOREIGN KEY `FK_LOT_ID_CHQ` (`LOT_ID`)
    REFERENCES `digicheck_dbo`.`LOTE` (`LOT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`BANCO`;
CREATE TABLE `digicheck_dbo`.`BANCO` (
  `BAN_ID` INT(10) NOT NULL AUTO_INCREMENT,
  `BAN_ABBA` VARCHAR(100) NOT NULL,
  `BAN_DESCRIPCION` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`BAN_ID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`DIVISA`;
CREATE TABLE `digicheck_dbo`.`DIVISA` (
  `DIV_ID` INT(10) NOT NULL AUTO_INCREMENT,
  `DIV_NOMBRE` VARCHAR(100) NOT NULL,
  `DIV_CODIGO` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`DIV_ID`)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS `digicheck_dbo`.`DOCUMENTO`;
CREATE TABLE `digicheck_dbo`.`DOCUMENTO` (
  `DOC_ID` INT(10) NOT NULL AUTO_INCREMENT,
  `DOC_NUMERO` INT(10) NOT NULL,
  `DOC_ARCHIVO` VARBINARY(1) NOT NULL,
  `DOT_ID` CHAR(1) NULL,
  `CHQ_ID` INT(10) NULL,
  PRIMARY KEY (`DOC_ID`),
  CONSTRAINT `FK_DOT_DOC` FOREIGN KEY `FK_DOT_DOC` (`DOT_ID`)
    REFERENCES `digicheck_dbo`.`TIPO_DOCUMENTO` (`DOT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CHQ_ID_DOC` FOREIGN KEY `FK_CHQ_ID_DOC` (`CHQ_ID`)
    REFERENCES `digicheck_dbo`.`CHEQUE` (`CHQ_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = INNODB;



SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------------------
-- EOF
