Create extension pgcrypto; -- cifrado de contraseñas

Create table usuario
(
  nombre_usuario varchar(20) not null,
  clave varchar(72) not null,

  primary key(nombre_usuario)
);

Create table amistad
(
  nombre_usuario varchar(20) not null,
  nombre_amigo varchar(60) not null,
  solicitudaceptada boolean not null,

  primary key (nombre_usuario, nombre_amigo),

  foreign key (nombre_usuario) references usuario (nombre_usuario)
   on delete cascade on update cascade
  foreign key (nombre_amigo) references usuario (nombre_usuario)
   on delete cascade on update cascade
);

Insert into usuario (nombre_usuario, clave) values ('Ines', crypt('1111', gen_salt('bf', 4)),TRUE);
Insert into usuario (nombre_usuario, clave) values ('Tato', crypt('8888', gen_salt('bf', 4)),TRUE);
Insert into usuario (nombre_usuario, clave) values ('Nico', crypt('1234', gen_salt('bf', 4)),TRUE);
Insert into usuario (nombre_usuario, clave) values ('Elena', crypt('4321', gen_salt('bf', 4)),TRUE);
Insert into usuario (nombre_usuario, clave) values ('Ivan', crypt('5555', gen_salt('bf', 4)),TRUE);

Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Ines','Tato', TRUE);
Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Ines','Nico', TRUE);
Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Ines','Ivan', TRUE);
Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Ivan','Elena', TRUE);
Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Nico','Elena', TRUE);
Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Elena','Nico', TRUE);
Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Nico','Ivan', TRUE);
Insert into amistad(nombre_usuario, nombre_amigo, solicitudaceptada) values ('Ivan','Nico', TRUE);