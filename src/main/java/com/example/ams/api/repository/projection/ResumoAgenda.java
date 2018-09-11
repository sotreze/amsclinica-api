package com.example.ams.api.repository.projection;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.LocalTime;


public class ResumoAgenda {

	private Long codigo;
	private Boolean ativo;
	private String email;
	private LocalDate data;
	//private String hora;
	private LocalTime hora;
	private String paciente;
	private String medico;
	private LocalDate dataAgendamento;
	private LocalTime horaAgendamento;

	public ResumoAgenda(Long codigo, Boolean ativo, String email, LocalDate data, LocalTime hora, String medico, String paciente, LocalDate dataAgendamento, LocalTime horaAgendamento) {
		super();
		this.codigo = codigo;
		this.ativo = ativo;
		this.email = email;
		this.data = data;
		this.hora = hora;
		this.paciente = paciente;
		this.medico = medico;
		this.dataAgendamento = dataAgendamento;
		this.horaAgendamento = horaAgendamento;
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
	
	/*public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}*/

	public LocalTime getHora() {
		return hora;
	}


	public void setHora(LocalTime hora) {
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

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public LocalTime getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(LocalTime horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}
	
}