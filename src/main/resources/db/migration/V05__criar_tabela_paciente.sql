CREATE TABLE Paciente (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	cpf VARCHAR(30),
	codigo_pessoa BIGINT(20),
	codigo_categoria BIGINT(20),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	UNIQUE (codigo_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
