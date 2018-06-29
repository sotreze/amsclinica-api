CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	primeiro_nome VARCHAR(50) ,
	sobrenome VARCHAR(50),
	email VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	senha VARCHAR(150) NOT NULL,
	UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (1, 'Administrador', 'O cara',  'sotreze@yahoo.com.br', 1, '$2a$10$dUNnU7lxaml5uIv/iO4XYOub.deGyt/P0T3Yw7l9O.M9zeeZ0KJ0u');
INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (2, 'Maria', 'Silva', 'maria.silva@ams.com.br', 1, '$2a$10$S3i0Iz7tvYCk/BQJHwoxEOilcH/2YbNg219LVpj2dIgq1kPsgTmHK');
INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (3, 'Maria', 'Alves', 'maria.alves@ams.com.br', 0, '$2a$10$S3i0Iz7tvYCk/BQJHwoxEOilcH/2YbNg219LVpj2dIgq1kPsgTmHK');

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_EXAME');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_EXAME');

INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_PESQUISAR_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_PESQUISAR_USUARIO');

INSERT INTO permissao (codigo, descricao) values (12, 'ROLE_CADASTRAR_FUNCIONARIO');
INSERT INTO permissao (codigo, descricao) values (13, 'ROLE_REMOVER_FUNCIONARIO');
INSERT INTO permissao (codigo, descricao) values (14, 'ROLE_PESQUISAR_FUNCIONARIO');

INSERT INTO permissao (codigo, descricao) values (15, 'ROLE_CADASTRAR_PACIENTE');
INSERT INTO permissao (codigo, descricao) values (16, 'ROLE_REMOVER_PACIENTE');
INSERT INTO permissao (codigo, descricao) values (17, 'ROLE_PESQUISAR_PACIENTE');

INSERT INTO permissao (codigo, descricao) values (18, 'ROLE_CADASTRAR_MEDICO');
INSERT INTO permissao (codigo, descricao) values (19, 'ROLE_REMOVER_MEDICO');
INSERT INTO permissao (codigo, descricao) values (20, 'ROLE_PESQUISAR_MEDICO');

INSERT INTO permissao (codigo, descricao) values (21, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (codigo, descricao) values (22, 'ROLE_REMOVER_CATEGORIA');
INSERT INTO permissao (codigo, descricao) values (23, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (codigo, descricao) values (24, 'ROLE_CADASTRAR_PRONTUARIO');
INSERT INTO permissao (codigo, descricao) values (25, 'ROLE_REMOVER_PRONTUARIO');
INSERT INTO permissao (codigo, descricao) values (26, 'ROLE_PESQUISAR_PRONTUARIO');

INSERT INTO permissao (codigo, descricao) values (27, 'ROLE_CADASTRAR_RECEITA');
INSERT INTO permissao (codigo, descricao) values (28, 'ROLE_REMOVER_RECEITA');
INSERT INTO permissao (codigo, descricao) values (29, 'ROLE_PESQUISAR_RECEITA');

INSERT INTO permissao (codigo, descricao) values (30, 'ROLE_CADASTRAR_MEDICACAO');
INSERT INTO permissao (codigo, descricao) values (31, 'ROLE_REMOVER_MEDICACAO');
INSERT INTO permissao (codigo, descricao) values (32, 'ROLE_PESQUISAR_MEDICACAO');

INSERT INTO permissao (codigo, descricao) values (33, 'ROLE_CADASTRAR_AGENDA');
INSERT INTO permissao (codigo, descricao) values (34, 'ROLE_REMOVER_AGENDA');
INSERT INTO permissao (codigo, descricao) values (35, 'ROLE_PESQUISAR_AGENDA');

INSERT INTO permissao (codigo, descricao) values (36, 'ROLE_CADASTRAR_PERIODO');
INSERT INTO permissao (codigo, descricao) values (37, 'ROLE_REMOVER_PERIODO');
INSERT INTO permissao (codigo, descricao) values (38, 'ROLE_PESQUISAR_PERIODO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 12);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 13);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 14);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 15);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 16);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 17);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 18);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 19);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 20);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 21);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 22);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 23);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 24);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 25);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 26);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 27);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 28);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 29);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 30);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 31);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 32);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 33);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 34);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 35);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 36);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 37);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 38);


-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 11);
