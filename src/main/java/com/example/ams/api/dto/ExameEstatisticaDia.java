package com.example.ams.api.dto;

import java.time.LocalDate;

import com.example.ams.api.model.TipoExame;

public class ExameEstatisticaDia {

	private TipoExame tipo;
	
	
	private LocalDate dia;
	

	public ExameEstatisticaDia(TipoExame tipo, LocalDate dia) {
		this.tipo = tipo;
		this.dia = dia;

	}

	public TipoExame getTipo() {
		return tipo;
	}

	public void setTipo(TipoExame tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}
	
}
