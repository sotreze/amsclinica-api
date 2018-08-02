CREATE TABLE periodo (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_agenda BIGINT(20),
	nome VARCHAR(30),
	data_inicial DATE NOT NULL,
	data_final DATE NOT NULL,
	ativo BOOLEAN NOT NULL,
	FOREIGN KEY (codigo_agenda) REFERENCES agenda(codigo),
	UNIQUE (codigo_agenda)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;