package com.example.ams.api.repository.agenda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Agenda;
import com.example.ams.api.repository.filter.AgendaFilter;
import com.example.ams.api.repository.projection.ResumoAgenda;

public interface AgendaRepositoryQuery {

	public Page<Agenda> filtrar(AgendaFilter agendaFilter, Pageable pageable);
	public Page<ResumoAgenda> resumir(AgendaFilter agendaFilter, Pageable pageable);

}