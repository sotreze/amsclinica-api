package com.example.ams.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

//import java.math.BigDecimal;
//import java.time.LocalTime;

//import com.example.ams.api.model.Medico;
//import com.example.ams.api.model.Paciente;

public class AgendaEstatisticaDia {

	//private Medico medico;
	
	//private Paciente paciente;
	
	private LocalDate dia;
	
	private LocalTime hora;

	//private BigDecimal total;

	public AgendaEstatisticaDia(LocalDate dia, LocalTime hora) {
		this.dia = dia;
		this.hora = hora;
		//this.medico = medico;
		//this.paciente = paciente;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


	/*public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}*/

	/*public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}*/

}
