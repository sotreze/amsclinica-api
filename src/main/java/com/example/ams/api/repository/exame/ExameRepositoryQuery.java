package com.example.ams.api.repository.exame;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Exame;
import com.example.ams.api.repository.filter.ExameFilter;
import com.example.ams.api.repository.projection.ResumoExame;

public interface ExameRepositoryQuery {
	
	public Page<Exame> filtrar(ExameFilter exameFilter, Pageable pageable);
	public Page<ResumoExame> resumir(ExameFilter exameFilter, Pageable pageable);

}
