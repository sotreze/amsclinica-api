package com.example.ams.api.repository.projection;



public class ResumoMedico {

	private Long codigo;
	private String nome;
	private String cpf;
	private String especializacao;
	private String crm;
	private String pessoa;

	public ResumoMedico(Long codigo, String nome, String cpf, String especializacao, String crm, String pessoa) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.especializacao = especializacao;
		this.crm = crm;
		this.pessoa = pessoa;
		
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
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

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}



}
