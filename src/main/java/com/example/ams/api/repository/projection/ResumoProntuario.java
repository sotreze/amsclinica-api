package com.example.ams.api.repository.projection;


public class ResumoProntuario {

	private Long codigo;
	private String paciente;
	private String medico;
	private String relatorio;

	public ResumoProntuario(Long codigo, String paciente, String medico,  String relatorio) {
		super();
		this.codigo = codigo;
		this.paciente = paciente;
		this.medico = medico;
		this.relatorio = relatorio;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	
	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}


}
