CREATE TABLE prontuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	anexo VARCHAR(200),
	codigo_pessoa BIGINT(20),
	receita VARCHAR(400),
	relatorio VARCHAR(600),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;