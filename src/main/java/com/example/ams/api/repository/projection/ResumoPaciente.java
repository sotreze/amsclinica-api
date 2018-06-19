package com.example.ams.api.repository.projection;


public class ResumoPaciente {

	private Long codigo;
	private String cpf;
	private String pessoa;
	private String categoria;
	private String prontuario;

	public ResumoPaciente(Long codigo, String cpf, String pessoa,  String categoria, String prontuario) {
		super();
		this.codigo = codigo;
		this.pessoa = pessoa;
		this.cpf = cpf;
		this.categoria = categoria;
		this.prontuario = prontuario;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

}
