package com.example.ams.api.repository.projection;

import com.example.ams.api.model.TipoPrescricao;

public class ResumoPrescricao {

	private Long codigo;
	private String descricao;
	private TipoPrescricao tipo;
	private String medico;
	private String paciente;
	private String medicacao;
	private String exame;

	public ResumoPrescricao(Long codigo, String descricao, TipoPrescricao tipo, String medico, String paciente, String medicacao, String exame) {
		super();
		this.codigo = codigo;
		this.medico = medico;
		this.medicacao = medicacao;
		this.paciente = paciente;
		this.descricao = descricao;
		this.tipo = tipo;
		this.exame = exame;

	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getPaciente() {
		return paciente;
	}


	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}


	public String getMedicacao() {
		return medicacao;
	}

	public void setMedicacao(String medicacao) {
		this.medicacao = medicacao;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public TipoPrescricao getTipo() {
		return tipo;
	}


	public void setTipo(TipoPrescricao tipo) {
		this.tipo = tipo;
	}


	public String getExame() {
		return exame;
	}


	public void setExame(String exame) {
		this.exame = exame;
	}

}
