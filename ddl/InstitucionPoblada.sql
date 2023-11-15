CREATE DATABASE Institucion;

USE Institucion;

CREATE TABLE `Administrador` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `correo` VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE Administrador
ADD CONSTRAINT unico_correo UNIQUE (correo);

CREATE TABLE `Docente` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NOT NULL,
	`apellido` VARCHAR(50) NOT NULL,
	`cedula` VARCHAR(20) NOT NULL,
	`telefono` VARCHAR(20) NOT NULL,
	`correo` VARCHAR(50) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE Docente
ADD CONSTRAINT unico_correo UNIQUE (correo);

ALTER TABLE Docente
ADD CONSTRAINT unico_cedula UNIQUE (cedula);

CREATE TABLE `Curso` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NOT NULL,
    `planEstudio` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Asignatura` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Grupo` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `numGrupo` INT NOT NULL,
    `id_curso` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_curso`) REFERENCES Curso(`id`)
);

CREATE TABLE `AsignaturasCurso` ( 
    `id_asignatura` INT NOT NULL,
    `id_curso` INT NOT NULL, 
    `horario` DATETIME NOT NULL,
    `lugar` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_asignatura`, `id_curso`), 
    FOREIGN KEY (`id_asignatura`) references Asignatura (`id`), 
    FOREIGN KEY (`id_curso`) references Curso (`id`) 
);

CREATE TABLE `GrupoProfesores` ( 
    `id_grupo` INT NOT NULL,
    `id_docente` INT NOT NULL, 
    PRIMARY KEY (`id_grupo`, `id_docente`), 
    FOREIGN KEY (`id_grupo`) references Grupo (`id`), 
    FOREIGN KEY (`id_docente`) references Docente (`id`) 
);

CREATE TABLE `Alumno` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `apellido` VARCHAR(50) NOT NULL,
    `numIdentificacion` VARCHAR(20) NOT NULL,
    `direccion` VARCHAR(20) NOT NULL,
    `telefono` VARCHAR(20) NOT NULL,
    `correo` VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `id_grupo` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_grupo`) REFERENCES Grupo(`id`)
);

ALTER TABLE Alumno
ADD CONSTRAINT unico_correo UNIQUE (correo);

ALTER TABLE Alumno
ADD CONSTRAINT unico_numIdentificacion UNIQUE (numIdentificacion);

CREATE TABLE `TemaExamen` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`descripcion` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Categoria` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`descripcion` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Configuracion` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `numPreguntas` INT NOT NULL,
    `tiempo` TIME NOT NULL,
    `peso` INT NOT NULL,
    `umbral_aprobado` INT NOT NULL,
    `fecha_presentacion` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Examen` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `descripcion` VARCHAR(200) NOT NULL,
    `id_tema_examen` INT NOT NULL,
    `id_categoria` INT NOT NULL,
    `id_docente` INT NOT NULL,
    `id_alumno` INT NOT NULL,
    `id_configuracion` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_tema_examen`) REFERENCES TemaExamen(`id`),
    FOREIGN KEY (`id_categoria`) REFERENCES Categoria(`id`),
    FOREIGN KEY (`id_docente`) REFERENCES Docente(`id`),
    FOREIGN KEY (`id_alumno`) REFERENCES Alumno(`id`),
    FOREIGN KEY (`id_configuracion`) REFERENCES Configuracion(`id`)
);

CREATE TABLE `Calificacion` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(200) NOT NULL,
    `notaTotal` INT NOT NULL,
    `id_examen` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_examen`) REFERENCES Examen(`id`)
);

CREATE TABLE `TipoPregunta` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Pregunta` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(200) NOT NULL,
    `porcentaje` INT NOT NULL,
    `id_tipo_pregunta` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_tipo_pregunta`) REFERENCES TipoPregunta(`id`)
);

CREATE TABLE `PreguntasExamen` ( 
    `id_pregunta` INT NOT NULL,
    `id_examen` INT NOT NULL, 
    PRIMARY KEY (`id_pregunta`, `id_examen`), 
    FOREIGN KEY (`id_pregunta`) references Pregunta (`id`), 
    FOREIGN KEY (`id_examen`) references Examen (`id`) 
);

CREATE TABLE `SubPregunta` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(200) NOT NULL,
    `porcentaje` INT NOT NULL,
    `id_pregunta` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_pregunta`) REFERENCES Pregunta(`id`)
);

CREATE TABLE `PrivacidadPregunta` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `BancoPreguntas` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_privacidad_pregunta` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_privacidad_pregunta`) REFERENCES PrivacidadPregunta(`id`)
);

CREATE TABLE `PreguntasBanco` ( 
    `id_pregunta` INT NOT NULL,
    `id_banco_pregunta` INT NOT NULL, 
    PRIMARY KEY (`id_pregunta`, `id_banco_pregunta`), 
    FOREIGN KEY (`id_pregunta`) references Pregunta (`id`), 
    FOREIGN KEY (`id_banco_pregunta`) references BancoPreguntas (`id`) 
);

CREATE TABLE `RespuestasPregunta` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `descripcion` VARCHAR(100) NOT NULL,
    `id_pregunta` INT NOT NUll,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_pregunta`) REFERENCES Pregunta (`id`)
);

insert into Curso (id, nombre, planEstudio) values (1, 'Matemáticas', 'Aplicación de conceptos matemáticos a problemas del mundo real, como la física, la ingeniería y la economía.');
insert into Curso (id, nombre, planEstudio) values (2, 'Ciencias', 'Se enfoca en la interacción entre la humanidad y el entorno natural, así como la conservación y sostenibilidad ambiental.');
insert into Curso (id, nombre, planEstudio) values (3, 'Negocios y Administración', 'Estudian la economía a nivel individual (micro) y a nivel de una economía completa (macro)');
insert into Curso (id, nombre, planEstudio) values (4, 'Ingeniería', 'Disciplina que combina la teoría científica con la aplicación práctica para diseñar y construir soluciones');
insert into Curso (id, nombre, planEstudio) values (5, 'Artes', 'Analiza la evolución del arte a lo largo de la historia y sus movimientos');

insert into Grupo (id, nombre, numGrupo, id_curso) values (1, 'Investigacion', 2, 1);
insert into Grupo (id, nombre, numGrupo, id_curso) values (2, 'Programacion', 1, 4);
insert into Grupo (id, nombre, numGrupo, id_curso) values (3, 'Ciencia', 3, 2);

insert into Docente (id, nombre, apellido, cedula, telefono, correo, password) values (1, 'Lindy', 'Charles', '98-901-1402', '+374 (276) 802-0407', 'lcharles0@nasa.gov', 'c4ca4238a0b923820dcc509a6f75849b');
insert into Docente (id, nombre, apellido, cedula, telefono, correo, password) values (2, 'Eben', 'Rochelle', '48-686-2551', '+48 (271) 149-1554', 'erochelle1@guardian.co.uk', 'c81e728d9d4c2f636f067f89cc14862c');
insert into Docente (id, nombre, apellido, cedula, telefono, correo, password) values (3, 'Cullen', 'Cutchie', '63-114-9654', '+86 (174) 951-8200', 'ccutchie2@nasa.gov', 'eccbc87e4b5ce2fe28308fd9f2a7baf3');

insert into Alumno (id, nombre, apellido, numIdentificacion, direccion, telefono, correo, password, id_grupo) values (1, 'Sylvia', 'Heddy', '88-753-0263', 'PO Box 59741', '+86 (850) 762-1176', 'sheddy0@reddit.com', 'c4ca4238a0b923820dcc509a6f75849b', 1);
insert into Alumno (id, nombre, apellido, numIdentificacion, direccion, telefono, correo, password, id_grupo) values (2, 'Clarence', 'Fildes', '55-103-6490', 'PO Box 91049', '+86 (213) 436-6313', 'cfildes1@amazon.co.jp', 'c81e728d9d4c2f636f067f89cc14862c', 1);
insert into Alumno (id, nombre, apellido, numIdentificacion, direccion, telefono, correo, password, id_grupo) values (3, 'Bethanne', 'Blackler', '03-229-9286', 'Suite 21', '+241 (675) 701-6056', 'bblackler2@nba.com', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', 2);

insert into TemaExamen (id, descripcion) values (1, 'Derivadas');
insert into TemaExamen (id, descripcion) values (2, 'Integrales');
insert into TemaExamen (id, descripcion) values (3, 'Bases de datos');

insert into Categoria (id, descripcion) values (1, 'proyecto');
insert into Categoria (id, descripcion) values (2, 'teorico');
insert into Categoria (id, descripcion) values (3, 'practico');
insert into Categoria (id, descripcion) values (4, 'Laboratorio');

insert into Configuracion (id, numPreguntas, tiempo, peso, umbral_aprobado, fecha_presentacion) values (1, 4, '1:30:00', 30, 60, '2023-10-25 09:00:00');
insert into Configuracion (id, numPreguntas, tiempo, peso, umbral_aprobado, fecha_presentacion) values (2, 4, '00:45:00', 40, 60, '2023-11-05 14:30:00');
insert into Configuracion (id, numPreguntas, tiempo, peso, umbral_aprobado, fecha_presentacion) values (3, 4, '01:00:00', 20, 60, '2023-12-15 10:15:00');

insert into Examen (id, nombre, descripcion, id_tema_examen, id_categoria, id_docente, id_alumno, id_configuracion) values (1, 'Examen de matemáticas', 'Este es un examen de matemáticas básicas', 1, 2, 1, 1, 2);
insert into Examen (id, nombre, descripcion, id_tema_examen, id_categoria, id_docente, id_alumno, id_configuracion) values (2, 'Examen de matemáticas', 'Este es un examen de matemáticas avanzado', 3, 3, 2, 3, 3);

insert into Calificacion (id, descripcion, notaTotal, id_examen) values (1, 'Calificación del Examen de Matemáticas', 75, 1);
insert into Calificacion (id, descripcion, notaTotal, id_examen) values (2, 'Calificación del Examen de Matemáticas', 80, 2);

insert into TipoPregunta (id, descripcion) values (1, 'Selección múltiple');
insert into TipoPregunta (id, descripcion) values (2, 'Verdadero y falso');
insert into TipoPregunta (id, descripcion) values (3, 'Emparejar');
insert into TipoPregunta (id, descripcion) values (4, 'Abierta');


insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (1, 'Encuentra la derivada de la función f(x)= 5', 1, 1);
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (2, 'Calcula la derivada de g(x)=3x^4', 2, 1);
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (3, '¿La derivada de h(x)= 2x + 7 es: 2?', 1, 2);
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (4, '¿La derivada de f(x)= 3x^4 + 7 es: 12x^2?', 1, 2);


insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (5, 'Calcula la integral de ∫4 dx', 1, 1);
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (6, 'Encuentra la integral de ∫3x^2 dx', 2, 1);
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (7, '¿La integral ∫(3x + 2)dx es: (3x^2/2)+2x+c?', 1, 2);
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (8, '¿La integral ∫(2x + 5)dx es: (x^2/2)+5x?', 1, 2);


insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (9, '¿Función principal de SGBD?', 1, 1); 
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (10, '¿Qué es un índice en una DB relacional?', 2, 1); 
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (11, 'La normalización es organizar datos de manera eficiente.', 1, 2);
insert into Pregunta (id, descripcion, porcentaje, id_tipo_pregunta) values (12, 'La clave primaria puede tener valores duplicados.', 1, 2);


insert into PreguntasExamen (id_pregunta, id_examen) values (1, 2);
insert into PreguntasExamen (id_pregunta, id_examen) values (2, 2);
insert into PreguntasExamen (id_pregunta, id_examen) values (3, 2);
insert into PreguntasExamen (id_pregunta, id_examen) values (4, 2);

insert into RespuestasPregunta (id, descripcion, id_pregunta) values (1, '0', 1);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (2, '5', 1);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (3, '5x', 1);

insert into RespuestasPregunta (id, descripcion, id_pregunta) values (4, '12x^3', 2);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (5, '3x^3', 2);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (6, '5x^2', 2);

insert into RespuestasPregunta (id, descripcion, id_pregunta) values (7, '4x', 5);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (8, '2x', 5);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (9, '4x^2', 5);

insert into RespuestasPregunta (id, descripcion, id_pregunta) values (10, 'x^3', 6);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (11, '2x', 6);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (12, '1', 6);

insert into RespuestasPregunta (id, descripcion, id_pregunta) values (13, 'Gestionar y organizar datos', 9);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (14, 'Crear presentaciones visuales', 9);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (15, 'Navegar por internet', 9);

insert into RespuestasPregunta (id, descripcion, id_pregunta) values (16, 'Estructura para mejorar la velocidad de busqueda', 10);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (17, 'Una tabla secundaria', 10);
insert into RespuestasPregunta (id, descripcion, id_pregunta) values (18, 'Una clave primaria', 10);