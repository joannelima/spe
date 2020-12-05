
-------------------------------------------- CREATES
-- Criando tabela de usuario
create table usuario (
	id serial primary key, 
	nome varchar(255), 
	sobrenome varchar(255),
	cpf varchar(255), 
	email varchar(255), 
	foto bytea
);

-- Criando tabela de perfil
create table perfil(
	id serial primary key, 
	descricao varchar(255)
);

-- Criando tabela de relacionamento entre usuario e perfil
create table usuario_perfil(
	fk_usuario integer,
	fk_perfil integer, 
	
	foreign key (fk_usuario) references usuario(id), 
	foreign key (fk_perfil) references perfil(id)
);

create table folha_ponto(
	id serial primary key, 
	dia date, 
	saldo time,
	horas_extras time, 
	debito time,
	fk_usuario integer, 
	
	foreign key (fk_usuario) references usuario(id)

);

-- criando tabela de registro
create table registro_ponto 
(
	id serial primary key, 
	fk_folha_ponto integer, 
	data_marcacao timestamp, 
	tipo_marcacao integer, 
	tipo_dia_semana varchar, 
	
	foreign key (fk_folha_ponto) references folha_ponto(id)
	
);
--------------------------------------------- INSERTS
--inserindo perfis
insert into perfil(descricao)
values ('ADMIN'), ('USUARIO');

-- inserindo usuario
insert into usuario (nome, sobrenome, cpf, email)
values ('TOQUIO', 'OLIVEIRA', '11111111111', 'toquio@gmail.com'), 
('BERLIM', 'SILVA', '02145841212', 'berlim@gmail.com');

-- inserindo relacionamento usuario e perfil
insert into usuario_perfil (fk_usuario, fk_perfil)
values (1, 1)
	   (2, 2)