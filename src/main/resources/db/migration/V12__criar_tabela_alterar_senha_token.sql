CREATE TABLE alterar_senha_token (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	expiry_date DATETIME,
	token VARCHAR(50),
	usuario_codigo BIGINT(20),
	FOREIGN KEY (usuario_codigo) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO alterar_senha_token (codigo, expiry_date, token, usuario_codigo) VALUES (1, '2017-01-01', 'expired-token', 1);
INSERT INTO alterar_senha_token (codigo, expiry_date, token, usuario_codigo) VALUES (2, '2019-01-01', 'valid-token', 2);