drop database if exists MonolithV2;
create database MonolithV2;
use MonolithV2;

create table Usuario(
	IDUsuario int auto_increment primary key not null,
	NombreUsuario varchar(100),
	Correo  varchar(70),
	Edad int,
	Pais varchar(50),
	Direccion varchar(200),
	Contrasena varchar(70),
	TipoUsuario int,
    Validado nvarchar(70),
	Puntos int not null
);

create table Validacion(
IdAdendum int primary key not null auto_increment,
Nombre nvarchar(50) not null,
adendum nvarchar(50) not null
);

select *from validacion;
delete from validacion where IdAdendum>0;
select * from Usuario;
delete from Usuario where IDUsuario>2;
select * from Validacion where adendum='';

delete from Validacion where Nombre='rick1234';

create table Conversacion(IDConversacion int not null primary key auto_increment,
DueñoCuentaUsuario int,UsuarioExterno int,
foreign key (DueñoCuentaUsuario) references Usuario(IDUsuario) on update cascade on delete cascade,
foreign key (UsuarioExterno) references Usuario(IDUsuario) on update cascade on delete cascade);

create table Mensaje (IDMensaje int not null primary key auto_increment, Contenido nvarchar (100),fecha date,
 Conversacion int, foreign key (Conversacion) references Conversacion(IDConversacion));
/*
create table Validacion(
	IdAdendum int primary key not null auto_increment,
	Nombre varchar(20) not null,
	adendum varchar (50) not null
);*/

create table Categoria(
	IDCategoria int auto_increment not null primary key,
	NombreCategoria varchar(100) not null,
	IDUsuario int not null,
	foreign key (IDUsuario) references Usuario(IDUsuario) on update cascade on delete cascade
);
select * from Categoria;

create table Actividad(
	IDActividad int not null primary key auto_increment,
	Nombre varchar(40),
	Fecha date,
	IDCategoria int,
	/*IDLocalizacion int,*/
	Estado boolean,
	foreign key (IDCategoria) references Categoria(IDCategoria) on update cascade on delete cascade  /*,
	foreign key (IDLocalizacion) references Localizacion(IDLocalizacion)*/
);

select * from actividad;
delete from actividad where IDActividad>1;

/*Aqui empiezan las tablas para el modulo grupos :v*/
create table Grupo(
	IDGrupo int not null primary key auto_increment,
	NombreGrupo nvarchar(100) not null
	#UsuarioLider int,
	/*PuntoReunion int,*/
	#foreign key(UsuarioLider) references Usuario(IDUsuario) on update cascade on delete cascade/*,
	#foreign key (PuntoReunion) references Localizacion(IDLocalizacion) on update cascade on delete cascade*/
);

create table Tarea(
	IDTarea int not null primary key auto_increment,
    IDGrupo int,
	Nombre nvarchar(100),
	Fecha datetime,
	Estado boolean,
    foreign key(IDGrupo) references Grupo(IDGrupo) on update cascade on delete cascade
);

create table catRol(
	IDRol int not null auto_increment primary key,
    rol varchar(15)
);

create table Miembros(
	IDMiembro int not null auto_increment primary key,
    IDUsuario int,
    IDGrupo int,
    IDRol int,
    foreign key(IDUsuario) references Usuario(IDUsuario) on update cascade on delete cascade,
    foreign key (IDGrupo) references Grupo(IDGrupo) on update cascade on delete cascade,
    foreign key (IDRol) references catRol(IDRol)
);

create table TareaMiembro(
	IDTareaMiembro int not null auto_increment primary key,
    IDTarea int,
    IDMiembro int,
    foreign key (IDTarea) references Tarea(IDTarea) on update cascade on delete cascade,
    foreign key (IDMiembro) references Miembros(IDMiembro) on update cascade on delete cascade
);

insert into catRol(rol) values('Lider');
insert into catRol(rol) values('Miembro');
/*Aqui terminan las tablas para el modulo grupos :v */

create table Nota(
	IDNota int not null auto_increment primary key,
	Nombre varchar(100),
	Contenido text,
	IDUsuario int,
	foreign key (IDUsuario) references Usuario(IDUsuario)
);
select * from Nota;

create table Logro(
	IDLogro int not null auto_increment primary key,
	IDUsuario int not null,
	Img int not null,
	Filtro int not null,
	Nombre varchar(100) not null,
	fecha date not null
);
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Validado,Puntos) 
values('rick','sasaas@gmail.com',21,'mexico','dasfdsfsdfsadfsd','memo',2,'Si',80);
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Validado,Puntos) 
values('memo','memo@fdsad.com',21,'mexico','dasfdsfsdfsadfsd','memo',1,'Si',80);
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Validado,Puntos) 
values('memo1','memo@fdsed.com',21,'mexico','dasfdsfsdfsadfsd','memo',1,'Si',30);
insert into Logro(IDUsuario,Img,Filtro,Nombre,fecha) 
values(1,2,5,'dogo #97','2017-05-12');
insert into Logro(IDUsuario,Img,Filtro,Nombre,fecha) 
values(2,1,7,'dogo #12','2017-03-01');
select * from Logro;
select * from Usuario;

create table Intercambio(
	IDInter int not null auto_increment primary key,
	IDusuarioDa int not null,
	IDusuarioRe int not null,
	UsuarioDa varchar(100) not null,
	UsuarioRe varchar(100) not null,
	IDdogoDa int not null,
	IDdogoRe int not null,
	FiltroDa varchar(100) not null,
	ImgdogoDa int not null,
	dogoDa varchar(100) not null,
	dogoRe int not null,
	Estado varchar(100) not null,
	fecha date not null
);
select * from Intercambio;

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

