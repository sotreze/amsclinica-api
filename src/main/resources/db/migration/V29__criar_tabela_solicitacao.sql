CREATE TABLE solicitacao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(400),
	data  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	anexo VARCHAR(200),
	codigo_agenda BIGINT(20) NOT NULL,
	codigo_paciente BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_agenda) REFERENCES agenda(codigo),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;