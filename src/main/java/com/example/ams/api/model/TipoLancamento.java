package com.example.ams.api.model;

public enum TipoLancamento {

	CONSULTA("Consulta"),
	EXAME("Exame")

	;

	private final String descricao;

	TipoLancamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
