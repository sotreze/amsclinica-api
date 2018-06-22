package com.example.ams.api.repository.projection;

import java.time.LocalDate;


public class ResumoPeriodo {

	private Long codigo;
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	public ResumoPeriodo(Long codigo, LocalDate dataInicial, LocalDate dataFinal) {
		super();
		this.codigo = codigo;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

}

