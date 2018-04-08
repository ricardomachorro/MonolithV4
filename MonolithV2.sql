drop database if exists MonolithV2;
create database MonolithV2;
use MonolithV2;

create table Localizacion(
	IDLocalizacion int auto_increment primary key not null,
	Nombre varchar (100),
	Latitud varchar(40) not null, 
	Longitud varchar (40) not null,
	Region varchar (100) not null
);

create table Usuario(
	IDUsuario int auto_increment primary key not null,
	NombreUsuario varchar(100),
	Correo  varchar(70),
	Edad int,
	Pais varchar(50),
	Direccion varchar(200),
	Contrasena varchar(70),
	Puntos int not null
);

create table Validacion(
	IdAdendum int primary key not null auto_increment,
	Nombre varchar(20) not null,
	adendum varchar (50) not null
);

#Comprobacion bien brgas de usuarios
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,Puntos) values('Cubic_85', 'juan.nevtor@hotmail.com', 21, 'México', 'Calle. Siempre viva No.48 Mz.S','1234567890', 0);
select * from Usuario;


create table Categoria(
	IDCategoria int auto_increment not null primary key,
	NombreCategoria varchar(100) not null,
	IDUsuario int not null,
	foreign key (IDUsuario) references Usuario(IDUsuario)
);

create table Actividad(
	IDActividad int not null primary key auto_increment,
	Nombre varchar(40),
	Fecha date,
	IDCategoria int,
	/*IDLocalizacion int,*/
	Estado boolean,
	foreign key (IDCategoria) references Categoria(IDCategoria)/*,
    foreign key (IDLocalizacion) references Localizacion(IDLocalizacion)*/
);

/*Aqui empiezan las tablas para el modulo grupos :v*/
create table Grupo(
	IDGrupo int not null primary key auto_increment,
	NombreGrupo nvarchar(100) not null,
	UsuarioLider int,
	/*PuntoReunion int,*/
	foreign key(UsuarioLider) references Usuario(IDUsuario) on update cascade on delete cascade/*,
	foreign key (PuntoReunion) references Localizacion(IDLocalizacion) on update cascade on delete cascade*/
);

#Comprobacion bien brgas de grupos
insert into Grupo(NombreGrupo, UsuarioLider) values('Hawkward', 1);
insert into Grupo(NombreGrupo, UsuarioLider) values('Fisica', 1);
select * from Grupo;

create table Tarea(
	IDTarea int not null primary key auto_increment,
	Nombre nvarchar(100),
	Fecha datetime,
	Estado boolean
);

#Comprobacion bien brgas de tareas
insert into Tarea(Nombre,Fecha,Estado) values('Modulo grupos', '1000-01-01 00:00:00', false);
insert into Tarea(Nombre,Fecha,Estado) values('Tesina', '1000-01-01 00:00:00', false);
insert into Tarea(Nombre,Fecha,Estado) values('Resnick', '1000-01-01 00:00:00', false);

create table TareaUsuario(
	IDTareaUsuario int not null auto_increment primary key,
	IDTarea int,
	IDGrupo int,
	foreign key (IDTarea) references Tarea(IDTarea) on update cascade on delete cascade,
    foreign key (IDGrupo) references Grupo(IDGrupo) on update cascade on delete cascade
);

#Comprobacion bien brgas de tareausuario
insert into TareaUsuario(IDTarea, IDGrupo) values(1,1);
insert into TareaUsuario(IDTarea, IDGrupo) values(2,1);
insert into TareaUsuario(IDTarea, IDGrupo) values(3,2);

/*Aqui terminan las tablas para el modulo grupos :v */

create table Nota(
	IDNota int not null auto_increment primary key,
	Nombre varchar(100),
	Conteneido text,
	IDUsuario int,
	foreign key (IDUsuario) references Usuario(IDUsuario)
);

create table Logro(
	IDLogro int not null auto_increment primary key,
	Nombre varchar(100),
	Descripcion text,
	Costo int
);

create table Estampa(
	IDEstampa int not null auto_increment primary key,
	Nombre varchar (100),
	Costo int
);

create table LogroUsuario(
	IDLogroUsuario int not null auto_increment primary key,
	IDUsuario int not null,
	IDLogro int not null,
	foreign key (IDUsuario) references Usuario(IDUsuario),
	foreign key (IDLogro) references Logro(IDLogro)
);

create table  EstampaUsaurio(
	IDEstampaUsuario int not null auto_increment primary key,
	IDUsuario int not null,
	IDEstampa int not null,
	foreign key (IDUsuario) references Usuario(IDUsuario),
	foreign key (IDEstampa) references Estampa(IDEstampa)
);
