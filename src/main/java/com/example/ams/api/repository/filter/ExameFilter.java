package com.example.ams.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ExameFilter {

	private String descricao;
	
	private String medico;
	
	private String paciente;
	
	private String tipoExame;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataExameDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataExameAte;

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

	public String getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}

	public LocalDate getDataExameDe() {
		return dataExameDe;
	}

	public void setDataExameDe(LocalDate dataExameDe) {
		this.dataExameDe = dataExameDe;
	}

	public LocalDate getDataExameAte() {
		return dataExameAte;
	}

	public void setDataExameAte(LocalDate dataExameAte) {
		this.dataExameAte = dataExameAte;
	}
	
}