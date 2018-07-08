CREATE TABLE funcionario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_pessoa BIGINT(20),
	nome VARCHAR(50) NOT NULL,
	cargo VARCHAR(70),
	setor VARCHAR(70),
	data_admissao DATE,
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
	UNIQUE (codigo_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;