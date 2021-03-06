CREATE TABLE exame (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	quantidade DECIMAL(10,2) DEFAULT 1 NOT NULL,
	descricao VARCHAR(400),
	data_agendamento  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	codigo_medico BIGINT(20) NOT NULL,
	codigo_paciente BIGINT(20) NOT NULL,
	codigo_tipoexame BIGINT(20),
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (codigo_tipoexame) REFERENCES tipoexame(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;