package com.example.ams.api.repository.projection;

import java.time.LocalDate;


public class ResumoAgenda {

	private Long codigo;
	private Boolean ativo;
	private LocalDate data;
	private String hora;
	private String paciente;
	private String medico;

	public ResumoAgenda(Long codigo, Boolean ativo, LocalDate data, String hora, String medico, String paciente) {
		super();
		this.codigo = codigo;
		this.ativo = ativo;
		this.data = data;
		this.hora = hora;
		this.paciente = paciente;
		this.medico = medico;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}
	

	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
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

}