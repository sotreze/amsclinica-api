package com.example.ams.api.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.ams.api.repository.listener.ProntuarioAnexoListener;

// import org.hibernate.validator.constraints.Email;
// import org.hibernate.validator.constraints.NotEmpty;

@EntityListeners(ProntuarioAnexoListener.class)
@Entity
@Table(name = "prontuario")
public class Prontuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	//@ManyToOne
	//@JoinColumn(name = "codigo_exame")
	//private String exame;
	
	private String anexo;

	@Transient
	private String urlAnexo;
	
	private String receita;
	
	//@NotEmpty
	private String relatorio;
	
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}*/

	public String getReceita() {
		return receita;
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
		Prontuario other = (Prontuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
