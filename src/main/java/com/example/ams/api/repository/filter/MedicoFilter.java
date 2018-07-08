package com.example.ams.api.repository.filter;



//import org.joda.time.DateTime;
//import org.springframework.format.annotation.DateTimeFormat;


public class MedicoFilter {
	
	private String nome;
	
	private String pessoa;

	//private String agenda;

	private String especializacao;

	private String crm;

	/*@DateTimeFormat(pattern = "yyyy-MM-dd")
	private DateTime horarioDisponivelDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private DateTime horarioDisponivelAte;*/
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}
	
	/*public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}*/

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

	/*public DateTime getHorarioDisponivelDe() {
		return horarioDisponivelDe;
	}

	public void setHorarioDisponivelDe(DateTime horarioDisponivelDe) {
		this.horarioDisponivelDe = horarioDisponivelDe;
	}

	public DateTime getHorarioDisponivelAte() {
		return horarioDisponivelAte;
	}

	public void setHorarioDisponivelAte(DateTime horarioDisponivelAte) {
		this.horarioDisponivelAte = horarioDisponivelAte;
	}*/
}
