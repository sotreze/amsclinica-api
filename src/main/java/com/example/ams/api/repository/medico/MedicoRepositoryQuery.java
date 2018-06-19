package com.example.ams.api.repository.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Medico;
import com.example.ams.api.repository.filter.MedicoFilter;
import com.example.ams.api.repository.projection.ResumoMedico;

public interface MedicoRepositoryQuery {

	public Page<Medico> filtrar(MedicoFilter medicoFilter, Pageable pageable);
	public Page<ResumoMedico> resumir(MedicoFilter medicoFilter, Pageable pageable);

}