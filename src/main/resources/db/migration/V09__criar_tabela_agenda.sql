CREATE TABLE agenda (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50) NOT NULL,
	codigo_medico BIGINT(20) NOT NULL,
	codigo_paciente BIGINT(20) NOT NULL,
	data DATE NOT NULL,
	hora TIME NOT NULL,
	ativo BOOLEAN NOT NULL,
	data_agendamento  DATE NOT NULL,
	hora_agendamento  TIME NOT NULL,
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo),
	FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
	UNIQUE (codigo_medico, data, hora)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;