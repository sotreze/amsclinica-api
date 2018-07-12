CREATE TABLE receita (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(400) NOT NULL,
	codigo_medico BIGINT(20) NOT NULL,
	codigo_paciente BIGINT(20) NOT NULL,
	codigo_medicacao BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (codigo_medicacao) REFERENCES medicacao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;