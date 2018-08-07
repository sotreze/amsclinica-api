package com.example.ams.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public class AgendaFilter {
	
	private Long codigo;
	
	private Boolean ativo;
	
	private String email;
	
	private String paciente;
	
	private String horario;
	
	private String medico;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	//@DateTimeFormat(pattern = "HH:mm")
	private String hora;
	
	
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
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
		
}
