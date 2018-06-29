package com.example.ams.api.repository.filter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


public class AgendaFilter {
	
	
	private Boolean ativo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHoraDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHoraAte;

	public LocalDateTime getDataHoraDe() {
		return dataHoraDe;
	}

	public void setDataHoraDe(LocalDateTime dataHoraDe) {
		this.dataHoraDe = dataHoraDe;
	}

	public LocalDateTime getDataHoraAte() {
		return dataHoraAte;
	}

	public void setDataHoraAte(LocalDateTime dataHoraAte) {
		this.dataHoraAte = dataHoraAte;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
