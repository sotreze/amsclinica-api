package com.example.ams.api.repository.projection;

import java.time.LocalDate;

public class ResumoSolicitacao {

	private Long codigo;
	private String descricao;
	private LocalDate dataSolicitacao;
	private String email;
	private String paciente;
	
	public ResumoSolicitacao(Long codigo, String descricao, LocalDate dataSolicitacao, String paciente, String email) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataSolicitacao = dataSolicitacao;
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

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
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
