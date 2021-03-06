package com.example.ams.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public class FuncionarioFilter {
	
	private String nome;
	
	private String cpf;

	private String cargo;

	private String setor;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAdmissaoDe;

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public LocalDate getDataAdmissaoDe() {
		return dataAdmissaoDe;
	}

	public void setDataAdmissaoDe(LocalDate dataAdmissaoDe) {
		this.dataAdmissaoDe = dataAdmissaoDe;
	}

}