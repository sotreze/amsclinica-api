CREATE TABLE prontuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	anexo VARCHAR(200),
	codigo_paciente BIGINT(20),
	receita VARCHAR(400),
	relatorio VARCHAR(600),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;