CREATE TABLE solicitacao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(400),
	email VARCHAR(50) NOT NULL,
	data  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	tipo VARCHAR(20) NOT NULL,
	anexo VARCHAR(200),
	codigo_paciente BIGINT(20),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;