CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cpf VARCHAR(30),
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	ativo BOOLEAN NOT NULL,
	telefone VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
