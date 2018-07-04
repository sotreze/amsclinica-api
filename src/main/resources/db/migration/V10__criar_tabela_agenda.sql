CREATE TABLE agenda (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo_medico BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	ativo BOOLEAN NOT NULL DEFAULT 1,
	data_hora DATETIME,
	FOREIGN KEY (codigo_medico) REFERENCES medico(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;