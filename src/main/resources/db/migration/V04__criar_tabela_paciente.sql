CREATE TABLE Paciente (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	codigo_pessoa BIGINT(20),
	codigo_categoria BIGINT(20),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	UNIQUE (codigo_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
