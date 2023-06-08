create database TrabajoFinal_TPV;
use TrabajoFinal_TPV;

CREATE TABLE `TrabajoFinal_TPV`.`Empleado` (
  `ID_Empleado` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(30) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Telefono` INT(9) NOT NULL,
  `Is_Admin` boolean,
  `Num_Empleado` INT NOT NULL,
  PRIMARY KEY (`ID_Empleado`));

CREATE TABLE `TrabajoFinal_TPV`.`Cliente` (
  `ID_Cliente` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(30) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Telefono` INT(9) NOT NULL,
  `Correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Cliente`));


  CREATE TABLE `TrabajoFinal_TPV`.`Nota` (
  `ID_Nota` INT NOT NULL AUTO_INCREMENT,
  `Titulo` VARCHAR(30) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Descripcion` VARCHAR(255) NOT NULL,
  `ID_Cliente` INT NOT NULL,
  PRIMARY KEY (`ID_Nota`),
  CONSTRAINT `fk_notas_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `TrabajoFinal_TPV`.`Cliente` (`ID_Cliente`)
	ON DELETE CASCADE
	ON UPDATE CASCADE);

CREATE TABLE `TrabajoFinal_TPV`.`Factura` (
  `ID_Factura` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NOT NULL,
  `Pagado` boolean default false,
  `Total` Double,
  `ID_Cliente` INT NOT NULL,
  PRIMARY KEY (`ID_Factura`),
  CONSTRAINT `fk_factura_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `TrabajoFinal_TPV`.`Cliente` (`ID_Cliente`)
	ON DELETE CASCADE
	ON UPDATE CASCADE);

CREATE TABLE `TrabajoFinal_TPV`.`Producto` (
  `ID_Producto` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(30) NOT NULL,
  `Precio` Double NOT NULL,
  `IVA` Double NOT NULL DEFAULT 21,
  PRIMARY KEY (`ID_Producto`));

CREATE TABLE `TrabajoFinal_TPV`.`Categoria` (
  `ID_Categoria` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ID_Categoria`));

CREATE TABLE `TrabajoFinal_TPV`.`Linea` (
`ID_Linea` INT NOT NULL AUTO_INCREMENT,
  `ID_Factura` INT NOT NULL,
  `ID_Producto` INT NOT NULL,
  `Cantidad` INT NOT NULL,
  `Sub_Total` Double NOT NULL,
  PRIMARY KEY (`ID_Linea`,`ID_Factura`,`ID_Producto`),
  CONSTRAINT `fk_factura_contenido` FOREIGN KEY (`ID_Factura`) REFERENCES `TrabajoFinal_TPV`.`Factura` (`ID_Factura`)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT `fk_factura_producto` FOREIGN KEY (`ID_Producto`) REFERENCES `TrabajoFinal_TPV`.`Producto` (`ID_Producto`)
	ON DELETE CASCADE
	ON UPDATE CASCADE);
    
    
    CREATE TABLE `TrabajoFinal_TPV`.`Contiene_pro_cat` (
  `ID_Categoria` INT NOT NULL,
  `ID_Producto` INT NOT NULL,
  PRIMARY KEY (`ID_Categoria`,`ID_Producto`),
  CONSTRAINT `fk_categoria_contiene` FOREIGN KEY (`ID_Categoria`) REFERENCES `TrabajoFinal_TPV`.`Categoria` (`ID_Categoria`)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT `fk_contiene_producto` FOREIGN KEY (`ID_Producto`) REFERENCES `TrabajoFinal_TPV`.`Producto` (`ID_Producto`)
	ON DELETE CASCADE
	ON UPDATE CASCADE);
