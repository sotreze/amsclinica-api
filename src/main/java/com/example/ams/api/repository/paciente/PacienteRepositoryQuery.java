package com.example.ams.api.repository.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Paciente;
import com.example.ams.api.repository.filter.PacienteFilter;
import com.example.ams.api.repository.projection.ResumoPaciente;

public interface PacienteRepositoryQuery {

	public Page<Paciente> filtrar(PacienteFilter pacienteFilter, Pageable pageable);
	public Page<ResumoPaciente> resumir(PacienteFilter pacienteFilter, Pageable pageable);

}
