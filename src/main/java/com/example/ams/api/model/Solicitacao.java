package com.example.ams.api.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	/*@JsonIgnoreProperties({"categoria", "medico"})
	@ManyToOne
	@JoinColumn(name = "codigo_agenda")
	private Agenda agenda;*/
	
	@NotNull
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;
	
	@NotNull
	private String descricao;
	
	private LocalDate data;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoSolicitacao tipo;
	
	private String anexo;

	@Transient
	private String urlAnexo;
	
	@JsonIgnore
	public boolean isCancelamento() {
		return TipoSolicitacao.CANCELAMENTO.equals(this.tipo);
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	/*public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public TipoSolicitacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoSolicitacao tipo) {
		this.tipo = tipo;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getUrlAnexo() {
		return urlAnexo;
	}

	public void setUrlAnexo(String urlAnexo) {
		this.urlAnexo = urlAnexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solicitacao other = (Solicitacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}