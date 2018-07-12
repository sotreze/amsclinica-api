package com.example.ams.api.repository.filter;

import java.time.LocalDate;
//import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


public class AgendaFilter {
	
	
	private Boolean ativo;
	
	private String medico;
	
	private String paciente;

	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAte;

	/*public LocalDateTime getDataHoraDe() {
		return dataHoraDe;
	}

	public void setDataHoraDe(LocalDateTime dataHoraDe) {
		this.dataHoraDe = dataHoraDe;
	}

	public LocalDateTime getDataHoraAte() {
		return dataHoraAte;
	}

	public void setDataHoraAte(LocalDateTime dataHoraAte) {
		this.dataHoraAte = dataHoraAte;
	}*/
	
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public LocalDate getDataDe() {
		return dataDe;
	}

	public void setDataDe(LocalDate dataDe) {
		this.dataDe = dataDe;
	}

	public LocalDate getDataAte() {
		return dataAte;
	}

	public void setDataAte(LocalDate dataAte) {
		this.dataAte = dataAte;
	}

}
