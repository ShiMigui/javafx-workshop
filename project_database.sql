DROP SCHEMA IF EXISTS javafxSellerDB;
CREATE SCHEMA javafxSellerDB;
USE javafxSellerDB;

CREATE TABLE department(
	cd_department SMALLINT AUTO_INCREMENT,
    nm_department VARCHAR(255) UNIQUE,
    CONSTRAINT pk_department PRIMARY KEY (cd_department)
);

CREATE TABLE seller(
	cd_seller INT AUTO_INCREMENT,
    nm_email VARCHAR(320) UNIQUE,
    nm_seller VARCHAR(255),
    dt_aniversario DATE,
    vl_salario DECIMAL(12, 2),
	cd_department SMALLINT,
    CONSTRAINT pk_seller PRIMARY KEY (cd_seller),
    CONSTRAINT fk_seller_department FOREIGN KEY (cd_department) REFERENCES department(cd_department)
);