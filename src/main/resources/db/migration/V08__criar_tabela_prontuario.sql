CREATE TABLE prontuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	anexo VARCHAR(200),
	relatorio VARCHAR(400),
	codigo_paciente BIGINT(20) NOT NULL,
	codigo_medico BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;