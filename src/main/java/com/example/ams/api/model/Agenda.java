package com.example.ams.api.model;

import java.time.LocalDate;
import java.time.LocalTime;

//import java.time.LocalDateTime;
//import javax.persistence.Column;
//import javax.persistence.Convert;
//import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Table;
import javax.persistence.Transient;
//import javax.validation.constraints.NotNull;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "agenda")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	/*@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name="data_hora")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataHora;*/
	
	private LocalDate data;
	
	private LocalTime hora;
	
    //@Column
    //@Convert(converter = BooleanSimNaoConverter.class)
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_medico")
	private Medico medico;
	
	@JsonIgnoreProperties("paciente")
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}*/
	
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
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
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
		Agenda other = (Agenda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
