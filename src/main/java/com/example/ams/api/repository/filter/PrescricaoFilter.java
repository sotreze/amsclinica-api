package com.example.ams.api.repository.filter;


public class PrescricaoFilter {

	private String descricao;
	
	private String medico;
	
	private String paciente;
	
	private String medicacao;
	
	private String exame;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

}
