CREATE TABLE prontuario (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	exame VARCHAR(400),
	receita VARCHAR(400),
	relatorio VARCHAR(600)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;