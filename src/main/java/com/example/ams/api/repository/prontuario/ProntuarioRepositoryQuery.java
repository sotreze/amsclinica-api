package com.example.ams.api.repository.prontuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Prontuario;
import com.example.ams.api.repository.filter.ProntuarioFilter;
import com.example.ams.api.repository.projection.ResumoProntuario;

public interface ProntuarioRepositoryQuery {

	public Page<Prontuario> filtrar(ProntuarioFilter prontuarioFilter, Pageable pageable);
	public Page<ResumoProntuario> resumir(ProntuarioFilter prontuarioFilter, Pageable pageable);

}
