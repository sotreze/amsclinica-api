package com.example.ams.api.repository.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ResumoAgenda {

	private Long codigo;
	private Boolean ativo;
	private String email;
	private LocalDate data;
	private String hora;
	private String paciente;
	private String medico;
	private LocalDateTime dataAgendamento;

	public ResumoAgenda(Long codigo, Boolean ativo, String email, LocalDate data, String hora, String medico, String paciente, LocalDateTime dataAgendamento) {
		super();
		this.codigo = codigo;
		this.ativo = ativo;
		this.email = email;
		this.data = data;
		this.hora = hora;
		this.paciente = paciente;
		this.medico = medico;
		this.dataAgendamento = dataAgendamento;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

}