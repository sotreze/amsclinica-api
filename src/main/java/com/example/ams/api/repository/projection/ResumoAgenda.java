package com.example.ams.api.repository.projection;

import java.time.LocalDateTime;


public class ResumoAgenda {

	private Long codigo;
	private Boolean ativo;
	private LocalDateTime dataHora;

	public ResumoAgenda(Long codigo, Boolean ativo, LocalDateTime dataHora) {
		super();
		this.codigo = codigo;
		this.ativo = ativo;
		this.dataHora = dataHora;
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

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

}
