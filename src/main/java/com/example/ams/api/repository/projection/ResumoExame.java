package com.example.ams.api.repository.projection;

import java.time.LocalDate;

public class ResumoExame {

	private Long codigo;
	private String descricao;
	private LocalDate data;
	private String medico;
	private String paciente;
	private String tipoExame;

	public ResumoExame(Long codigo, String descricao, LocalDate data, String medico, String paciente, String tipoExame) {
		super();
		this.codigo = codigo;
		this.medico = medico;
		this.paciente = paciente;
		this.descricao = descricao;
		this.data = data;
		this.tipoExame = tipoExame;
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
	
	public String getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}