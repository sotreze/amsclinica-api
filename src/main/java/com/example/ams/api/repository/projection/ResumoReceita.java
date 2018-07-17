package com.example.ams.api.repository.projection;

public class ResumoReceita {

	private Long codigo;
	private String descricao;
	private String medico;
	private String paciente;
	private String medicacao;

	public ResumoReceita(Long codigo, String descricao, String medico, String paciente, String medicacao) {
		super();
		this.codigo = codigo;
		this.medico = medico;
		this.medicacao = medicacao;
		this.paciente = paciente;
		this.descricao = descricao;
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

}
