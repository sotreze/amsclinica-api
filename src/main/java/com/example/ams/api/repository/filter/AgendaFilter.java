package com.example.ams.api.repository.filter;


import java.time.LocalTime;

//import org.springframework.format.annotation.DateTimeFormat;


public class AgendaFilter {

	private String diaSemana;

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalTime horaDe;
	
	private LocalTime horaAte;


	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHora() {
		return horaDe;
	}

	public void setHora(LocalTime horaDe) {
		this.horaDe = horaDe;
	}
	
	public LocalTime getHoraAte() {
		return horaAte;
	}

	public void setHoraAte(LocalTime horaAte) {
		this.horaAte = horaAte;
	}

}
