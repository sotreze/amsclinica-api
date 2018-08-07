CREATE TABLE agenda (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50) NOT NULL,
	codigo_medico BIGINT(20) NOT NULL,
	codigo_paciente BIGINT(20) NOT NULL,
	hora_horario TIME,
	data DATE NOT NULL,
	hora VARCHAR(12) NOT NULL,
	ativo BOOLEAN NOT NULL,
	data_agendamento  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	FOREIGN KEY (hora_horario) REFERENCES horario(hora),
	UNIQUE (codigo_medico, data, hora)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;