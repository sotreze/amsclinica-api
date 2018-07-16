CREATE TABLE prescricao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(400),
	tipo VARCHAR(20) NOT NULL,
	codigo_medico BIGINT(20) NOT NULL,
	codigo_paciente BIGINT(20) NOT NULL,
	codigo_medicacao BIGINT(20),
	codigo_exame BIGINT(20),
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (codigo_medicacao) REFERENCES medicacao(codigo),
	FOREIGN KEY (codigo_exame) REFERENCES exame(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;