package com.example.ams.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.ams.api.model.TipoLancamento;

public class ResumoLancamento {

	private Long codigo;
	private String descricao;
	private BigDecimal numeroDesenho;
	private LocalDate dataDesenho;
	private LocalDate dataCadastro;
	private LocalDate dataLimite;
	private BigDecimal valor;
	private BigDecimal pesoPeca;
	private BigDecimal pesoPorMaterial;
	private String composicaoQuimica;
	private String clienteDestino;
	private Long codFornecedor;
	private TipoLancamento tipo;
	private String material;
	private String pessoa;


	public ResumoLancamento(Long codigo, String descricao, LocalDate dataDesenho, LocalDate dataCadastro, LocalDate dataLimite,
			BigDecimal valor, TipoLancamento tipo, String material, String pessoa) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataDesenho = dataDesenho;
		this.dataCadastro = dataCadastro;
		this.dataLimite = dataLimite;
		this.valor = valor;
		this.tipo = tipo;
		this.material = material;
		this.pessoa = pessoa;
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

	public BigDecimal getNumeroDesenho() {
		return numeroDesenho;
	}

	public void setNumeroDesenho(BigDecimal numeroDesenho) {
		this.numeroDesenho = numeroDesenho;
	}

	public LocalDate getDataDesenho() {
		return dataDesenho;
	}

	public void setDataDesenho(LocalDate dataDesenho) {
		this.dataDesenho = dataDesenho;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getPesoPeca() {
		return pesoPeca;
	}

	public void setPesoPeca(BigDecimal pesoPeca) {
		this.pesoPeca = pesoPeca;
	}

	public BigDecimal getPesoPorMaterial() {
		return pesoPorMaterial;
	}

	public void setPesoPorMaterial(BigDecimal pesoPorMaterial) {
		this.pesoPorMaterial = pesoPorMaterial;
	}

	public String getComposicaoQuimica() {
		return composicaoQuimica;
	}

	public void setComposicaoQuimica(String composicaoQuimica) {
		this.composicaoQuimica = composicaoQuimica;
	}

	public String getClienteDestino() {
		return clienteDestino;
	}

	public void setClienteDestino(String clienteDestino) {
		this.clienteDestino = clienteDestino;
	}

	public Long getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(Long codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

}
