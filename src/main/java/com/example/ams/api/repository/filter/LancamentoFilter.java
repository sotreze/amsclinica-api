package com.example.ams.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

	private String descricao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataConsultaDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataConsultaAte;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataConsultaDe() {
		return dataConsultaDe;
	}

	public void setDataConsultaDe(LocalDate dataConsultaDe) {
		this.dataConsultaDe = dataConsultaDe;
	}

	public LocalDate getDataConsultaAte() {
		return dataConsultaAte;
	}

	public void setDataConsultaAte(LocalDate dataConsultaAte) {
		this.dataConsultaAte = dataConsultaAte;
	}

}
