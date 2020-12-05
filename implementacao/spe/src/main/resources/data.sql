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
