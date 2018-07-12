package com.example.ams.api.dto;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.ams.api.model.Medicacao;
import com.example.ams.api.model.Medico;

public class ReceitaGerar {
	
	@OneToOne
	private Medico medico;
	
	@OneToMany
	@JoinColumn(name = "codigo_medicacao")
	private Medicacao medicacao;
	
	private String descricao;

	public ReceitaGerar(Medico medico, Medicacao medicacao, String descricao) {
		this.medico = medico;
		this.medicacao = medicacao;
		this.descricao = descricao;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Medicacao getMedicacao() {
		return medicacao;
	}

	public void setMedicacao(Medicacao medicacao) {
		this.medicacao = medicacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
