CREATE TABLE medico (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_pessoa BIGINT(20) NOT NULL,
	codigo_agenda BIGINT(20),
	especializacao VARCHAR(400),
	crm VARCHAR(30),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
	FOREIGN KEY (codigo_agenda) REFERENCES agenda(codigo),
	UNIQUE (codigo_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;