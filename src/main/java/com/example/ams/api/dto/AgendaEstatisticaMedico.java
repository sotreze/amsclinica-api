package com.example.ams.api.dto;

import com.example.ams.api.model.Paciente;
import com.example.ams.api.model.Medico;

public class AgendaEstatisticaMedico {

	private Medico medico;

	private Paciente paciente;


	public AgendaEstatisticaMedico(Medico medico, Paciente paciente) {
		this.medico = medico;
		this.paciente = paciente;
	}

	public Medico getMedico() {
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
	}

}

