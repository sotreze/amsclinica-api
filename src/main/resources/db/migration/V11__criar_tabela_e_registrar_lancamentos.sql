CREATE TABLE lancamento (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_consulta DATE NOT NULL,
	data_exame DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(600),
	tipo VARCHAR(20) NOT NULL,
	codigo_exame BIGINT(20),
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_exame) REFERENCES exame(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

