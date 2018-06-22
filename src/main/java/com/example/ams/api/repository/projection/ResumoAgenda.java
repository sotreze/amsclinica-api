package com.example.ams.api.repository.projection;

import java.time.LocalTime;


public class ResumoAgenda {

	private Long codigo;
	private String diaSemana;
	private LocalTime hora;

	public ResumoAgenda(Long codigo, String diaSemana,  LocalTime hora) {
		super();
		this.codigo = codigo;
		this.diaSemana = diaSemana;
		this.hora = hora;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

}
