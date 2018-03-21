create database Servicios;
use Servicios;
create table Servicios(
	IDServicio int auto_increment primary key,
    NombreServicio varchar(30)
);
insert into Servicios(NombreServicio) values("Ingreso");