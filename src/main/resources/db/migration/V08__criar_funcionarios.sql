CREATE TABLE funcionario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_pessoa BIGINT(20),
	cargo VARCHAR(70),
	setor VARCHAR(70),
	data_admissao DATE,
	cpf VARCHAR(30),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
	UNIQUE (codigo_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;