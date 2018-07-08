package com.example.ams.api.model;

import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String especializacao;
	
	@NotNull
	private String crm;


	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;
	
	//@NotNull
	/*@OneToOne
	@JoinColumn(name = "codigo_agenda")
	private Agenda agenda;*/
	
	@JsonIgnoreProperties("medico")
	@Valid
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Agenda> agendas;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getEspecializacao() {
		return especializacao;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}
	
	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}	
	
	/*public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}*/
	
	/*public List<Agenda> getAgendas() {
		return agendas;
	}
	
	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}*/


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
		Medico other = (Medico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
