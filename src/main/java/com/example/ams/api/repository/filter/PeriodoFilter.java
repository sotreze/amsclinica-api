package com.example.ams.api.repository.filter;


import java.time.LocalDate;


public class PeriodoFilter {
	
	private String nome;

	private LocalDate dataInicialDe;
	
	private LocalDate dataInicialAte;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataInicialDe() {
		return dataInicialDe;
	}

	public void setDataInicialDe(LocalDate dataInicialDe) {
		this.dataInicialDe = dataInicialDe;
	}

	public LocalDate getDataInicialAte() {
		return dataInicialAte;
	}

	public void setDataInicialAte(LocalDate dataInicialAte) {
		this.dataInicialAte = dataInicialAte;
	}

}

