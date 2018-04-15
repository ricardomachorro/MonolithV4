#Comprobacion bien brgas de usuarios
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Puntos) 
values('Cubic85', 'juan.nevtor@hotmail.com', 21, 'México', 'Calle. Siempre viva No.48 Mz.S','1234567890', 1, 10);
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Puntos) 
values('MemoWolf', 'guille.rami@hotmail.com', 21, 'México', 'Calle. Siempre viva No.48 Mz.S','1234567890', 1, 10);
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Puntos) 
values('RickMacho', 'albert.macho@hotmail.com', 21, 'México', 'Calle. Siempre viva No.48 Mz.S','1234567890', 1, 10);
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Puntos) 
values('RaulRojas', 'daniel.rojsan@hotmail.com', 21, 'México', 'Calle. Siempre viva No.48 Mz.S','1234567890', 1, 10);
insert into Usuario(NombreUsuario,Correo,Edad,Pais,Direccion,Contrasena,TipoUsuario,Puntos) 
values('AlexUchiha', 'alejan.enriri@hotmail.com', 21, 'México', 'Calle. Siempre viva No.48 Mz.S','1234567890', 1, 10);
select * from Usuario;

#Comprobacion bien brgas de grupos
insert into Grupo(NombreGrupo) values('Hawkward');#ID=1
insert into Grupo(NombreGrupo) values('Fisica');#ID=2
select * from Grupo;

#Comprobacion bien brgas de tarea
insert into Tarea(Nombre,IDGrupo,Fecha,Estado) values('Modulo grupos', 1,'1000-01-01', false);#ID=1
insert into Tarea(Nombre,IDGrupo,Fecha,Estado) values('Tesina', 1,'1000-01-01', true);#ID=2
insert into Tarea(Nombre,IDGrupo,Fecha,Estado) values('Resnick', 2,'1000-01-01', false);#ID=3
select * from Tarea;

#Comprobacion bien vrgas de miembros
insert into Miembros(IDUsuario,IDGrupo,IDRol) values(1,1,1);#ID=1
insert into Miembros(IDUsuario,IDGrupo,IDRol) values(2,1,2);#ID=2
insert into Miembros(IDUsuario,IDGrupo,IDRol) values(3,1,2);#ID=3
insert into Miembros(IDUsuario,IDGrupo,IDRol) values(4,1,2);#ID=4
insert into Miembros(IDUsuario,IDGrupo,IDRol) values(5,1,2);#ID=5
insert into Miembros(IDUsuario,IDGrupo,IDRol) values(1,2,1);#ID=1
select * from Miembros;

#Comprobacion bien vrgas de tareamiembro
insert into TareaMiembro(IDTarea,IDMiembro) values (2,1);
insert into TareaMiembro(IDTarea,IDMiembro) values (2,2);
insert into TareaMiembro(IDTarea,IDMiembro) values (2,3);
insert into TareaMiembro(IDTarea,IDMiembro) values (2,4);
insert into TareaMiembro(IDTarea,IDMiembro) values (2,5);
insert into TareaMiembro(IDTarea,IDMiembro) values (3,1);
select * from TareaMiembro;

#Traer la id del usuario por su nombre
select IDUsuario from Usuario where NombreUsuario='memo_wolf';

#Traer el nombre de usuario por el correo
select NombreUsuario from Usuario where Correo='juan.nevtor@hotmail.com';

#Traer las tareas de un grupo alv
select * from Tarea where IDGrupo=1;

#Seleccionar los miembros de una tarea
select NombreUsuario from Usuario 
inner join Miembros on Usuario.IDUsuario=Miembros.IDUsuario
inner join TareaMiembro on Miembros.IDMiembro=TareaMiembro.IDMiembro
where TareaMiembro.IDTarea=2;

#Traer los grupos donde un usuario es miembro
select * from Grupo 
inner join Miembros on Grupo.IDGrupo=Miembros.IDGrupo
where Miembros.IDUsuario=1;