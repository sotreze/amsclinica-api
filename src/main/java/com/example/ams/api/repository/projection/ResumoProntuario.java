package com.example.ams.api.repository.projection;


public class ResumoProntuario {

	private Long codigo;
	private String exame;
	private String receita;
	private String relatorio;

	public ResumoProntuario(Long codigo, String exame,  String receita,  String relatorio) {
		super();
		this.codigo = codigo;
		this.exame = exame;
		this.receita = receita;
		this.relatorio = relatorio;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

	public String getReceita() {
		return receita;
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}


}
