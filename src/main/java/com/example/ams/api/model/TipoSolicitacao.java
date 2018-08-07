package com.example.ams.api.model;

public enum TipoSolicitacao {
	
	CANCELAMENTO("Cancelamento"),
	SOLICITACAO("Solicitação")

	;

	private final String descricao;

	TipoSolicitacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
