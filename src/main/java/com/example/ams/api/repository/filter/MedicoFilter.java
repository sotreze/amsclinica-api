package com.example.ams.api.repository.filter;



//import org.joda.time.DateTime;
//import org.springframework.format.annotation.DateTimeFormat;


public class MedicoFilter {

	private String especializacao;

	private String crm;

	/*@DateTimeFormat(pattern = "yyyy-MM-dd")
	private DateTime horarioDisponivelDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private DateTime horarioDisponivelAte;*/


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
