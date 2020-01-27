# Proyecto Hibernate
Proyecto desarrollado en Java que hace uso de Hibernate para el mapeo de objetos conectando a una base de datos MySQL.
El proyecto ejecuta un menú que permite realizar operaciones básicas sobre la base de datos.

## Script de la base de datos

```sql
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE `departamento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `departamento` (`id`, `nombre`) VALUES
(1, 'Informática'),
(2, 'RRHH'),
(3, 'Producción'),
(4, 'Desarrollo');

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `id_dep` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `empleado` (`id`, `nombre`, `apellido`, `id_dep`) VALUES
(2, 'Empleado', '1', 1),
(3, 'Empleado', '2', 1),
(4, 'Empleado', '2', 5);


ALTER TABLE `departamento`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ID_DEP_FK` (`id_dep`);


ALTER TABLE `departamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;


ALTER TABLE `empleado`
  ADD CONSTRAINT `ID_DEP_FK` FOREIGN KEY (`id_dep`) REFERENCES `departamento` (`id`);

```
