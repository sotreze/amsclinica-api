package com.example.ams.api.repository.periodo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Periodo;
import com.example.ams.api.repository.filter.PeriodoFilter;
import com.example.ams.api.repository.projection.ResumoPeriodo;

public interface PeriodoRepositoryQuery {

	public Page<Periodo> filtrar(PeriodoFilter periodoFilter, Pageable pageable);
	public Page<ResumoPeriodo> resumir(PeriodoFilter periodoFilter, Pageable pageable);

}