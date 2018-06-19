package com.example.ams.api.repository.receita;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Receita;
import com.example.ams.api.repository.filter.ReceitaFilter;
import com.example.ams.api.repository.projection.ResumoReceita;

public interface ReceitaRepositoryQuery {

	public Page<Receita> filtrar(ReceitaFilter receitaFilter, Pageable pageable);
	public Page<ResumoReceita> resumir(ReceitaFilter receitaFilter, Pageable pageable);

}
