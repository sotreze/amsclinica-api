package com.example.ams.api.repository.projection;

import java.time.LocalDate;

public class ResumoSolicitacao {

	private Long codigo;
	private String descricao;
	private LocalDate data;
	private String email;
	private String paciente;
	
	public ResumoSolicitacao(Long codigo, String descricao, LocalDate data, String paciente, String email) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.data = data;
		this.email = email;
		this.paciente = paciente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	

}
