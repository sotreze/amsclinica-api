package com.example.ams.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class SolicitacaoFilter {
	
	private Long codigo;
	
	private String email;
	
	private String paciente;
	
	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSolicitacao;
	
	private String tipoSolicitacao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSolicitacaoDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataSolicitacaoAte;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public LocalDate getDataSolicitacaoDe() {
		return dataSolicitacaoDe;
	}

	public void setDataSolicitacaoDe(LocalDate dataSolicitacaoDe) {
		this.dataSolicitacaoDe = dataSolicitacaoDe;
	}

	public LocalDate getDataSolicitacaoAte() {
		return dataSolicitacaoAte;
	}

	public void setDataSolicitacaoAte(LocalDate dataSolicitacaoAte) {
		this.dataSolicitacaoAte = dataSolicitacaoAte;
	}
	
}