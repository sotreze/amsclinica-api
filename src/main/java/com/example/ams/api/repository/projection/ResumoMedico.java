package com.example.ams.api.repository.projection;



public class ResumoMedico {

	private Long codigo;
	private String nome;
	private String especializacao;
	private String crm;
	//private String agenda;
	private String pessoa;

	public ResumoMedico(Long codigo, String nome, String especializacao, String crm, String pessoa) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.especializacao = especializacao;
		this.crm = crm;
		//this.agenda = agenda;
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
	
	/*public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}*/
	
	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}



}
