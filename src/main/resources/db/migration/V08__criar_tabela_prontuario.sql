CREATE TABLE prontuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	anexo VARCHAR(200),
	codigo_paciente BIGINT(20),
	codigo_medico BIGINT(20),
	relatorio VARCHAR(600),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;