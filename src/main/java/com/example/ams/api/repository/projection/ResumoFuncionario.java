package com.example.ams.api.repository.projection;

import java.time.LocalDate;


public class ResumoFuncionario {

	private Long codigo;
	private String cargo;
	private String setor;
	private String pessoa;
	private LocalDate dataAdmissao;
	private String cpf;

	public ResumoFuncionario(Long codigo, String pessoa,  String cargo,  String setor, LocalDate dataAdmissao, String cpf) {
		super();
		this.codigo = codigo;
		this.pessoa = pessoa;
		this.cargo = cargo;
		this.setor = setor;
		this.dataAdmissao = dataAdmissao;
		this.cpf = cpf;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
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

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


}
