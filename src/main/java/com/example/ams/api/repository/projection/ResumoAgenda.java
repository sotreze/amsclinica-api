package com.example.ams.api.repository.projection;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.LocalTime;


public class ResumoAgenda {

	private Long codigo;
	private Boolean ativo;
	private LocalDate data;
	private LocalTime hora;

	public ResumoAgenda(Long codigo, Boolean ativo, LocalDate data, LocalTime hora) {
		super();
		this.codigo = codigo;
		this.ativo = ativo;
		this.data = data;
		this.hora = hora;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public LocalTime getHora() {
		return hora;
	}


	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

}
