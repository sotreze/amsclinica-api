package com.example.ams.api.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "prescricao")
public class Prescricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_medico")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;

	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPrescricao tipo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_medicacao")
	private Medicacao medicacao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_exame")
	private Exame exame;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public TipoPrescricao getTipo() {
		return tipo;
	}

	public void setTipo(TipoPrescricao tipo) {
		this.tipo = tipo;
	}

	public Medicacao getMedicacao() {
		return medicacao;
	}

	public void setMedicacao(Medicacao medicacao) {
		this.medicacao = medicacao;
	}
	
	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
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
		Prescricao other = (Prescricao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}