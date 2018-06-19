package com.example.ams.api.repository.projection;

import org.joda.time.DateTime;


public class ResumoMedico {

	private Long codigo;
	private String especializacao;
	private DateTime horarioDisponivel;
	private String crm;

	public ResumoMedico(Long codigo, String especializacao,  DateTime horarioDisponivel, String crm) {
		super();
		this.codigo = codigo;
		this.especializacao = especializacao;
		this.horarioDisponivel = horarioDisponivel;
		this.crm = crm;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public DateTime getHorarioDisponivel() {
		return horarioDisponivel;
	}

	public void setHorarioDisponivel(DateTime horarioDisponivel) {
		this.horarioDisponivel = horarioDisponivel;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}


}
