package com.example.ams.api.model;

public enum TipoPrescricao {
	
	RECEITA("Receita"),
	EXAME("Exame")

	;

	private final String descricao;

	TipoPrescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

