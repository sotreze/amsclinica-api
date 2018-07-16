package com.example.ams.api.repository.prescricao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Prescricao;
import com.example.ams.api.repository.filter.PrescricaoFilter;
import com.example.ams.api.repository.projection.ResumoPrescricao;

public interface PrescricaoRepositoryQuery {
	
	public Page<Prescricao> filtrar(PrescricaoFilter prescricaoFilter, Pageable pageable);
	public Page<ResumoPrescricao> resumir(PrescricaoFilter prescricaoFilter, Pageable pageable);

}
