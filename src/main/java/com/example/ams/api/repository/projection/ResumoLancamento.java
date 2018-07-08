package com.example.ams.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.ams.api.model.TipoLancamento;

public class ResumoLancamento {

	private Long codigo;
	private String descricao;
	private LocalDate dataConsulta;
	private LocalDate dataExame;
	private BigDecimal valor;
	private String observacao;
	private TipoLancamento tipo;
	private String exame;
	private String paciente;


	public ResumoLancamento(Long codigo, String descricao, LocalDate dataConsulta, LocalDate dataExame,
			BigDecimal valor, TipoLancamento tipo, String exame, String paciente) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataConsulta = dataConsulta;
		this.dataExame = dataExame;
		this.valor = valor;
		this.tipo = tipo;
		this.exame = exame;
		this.paciente = paciente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public LocalDate getDataExame() {
		return dataExame;
	}

	public void setDataExame(LocalDate dataExame) {
		this.dataExame = dataExame;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

}
