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
	codigo BIGINT(20),
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (1, 'Maria', 'Silva', 'maria.silva@yahoo.com.br', 1, '$2a$10$BzRwflIG6FFmaVpEF.dTieQKT0zemP4A3COmsPwm97gHOVZUvQ/Py');
INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (2, 'Fernanda', 'Alves', 'fernanda.alves@ams.com.br', 1, '$2a$10$4ITNriTCLg2exezyCj8lZeXUbkn7EurPYr5vTZlhrjmr.ZDyRgh2u');
INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (3, 'Isabela', 'Silva',  'isabela.silva@ams.com.br', 1, '$2a$10$VwyvntQMi7BGA3xDCjCSwOmb8gWek4BbGGu6xNk1xjF91dN8BCQHS');
INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (4, 'Adonildo', 'Filho',  'adonildo.filho@ams.com.br', 1, '$2a$10$dUNnU7lxaml5uIv/iO4XYOub.deGyt/P0T3Yw7l9O.M9zeeZ0KJ0u');
INSERT INTO usuario (codigo, primeiro_nome, sobrenome, email, ativo, senha) values (5, 'Márcio', 'Sousa',  'sotreze@yahoo.com.br', 1, '$2a$10$dUNnU7lxaml5uIv/iO4XYOub.deGyt/P0T3Yw7l9O.M9zeeZ0KJ0u');

INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_USUARIO');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_FUNCIONARIO');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_MEDICO');
INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_ADMINISTRADOR');

-- maria usuaria
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (1, 1, 1);

-- fernanda funcionaria
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (2, 2, 1);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (3, 2, 2);

-- Isabela medica
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (4, 3, 1);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (5, 3, 2);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (6, 3, 3);

-- Adonildo administrador
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (7, 4, 1);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (8, 4, 2);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (9, 4, 3);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (10, 4, 4);

-- Marcio administrador
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (11, 5, 1);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (12, 5, 2);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (13, 5, 3);
INSERT INTO usuario_permissao (codigo, codigo_usuario, codigo_permissao) values (14, 5, 4);


