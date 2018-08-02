package com.example.ams.api.repository.solicitacao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Solicitacao;
import com.example.ams.api.repository.filter.SolicitacaoFilter;
import com.example.ams.api.repository.projection.ResumoSolicitacao;

public interface SolicitacaoRepositoryQuery {

	public Page<Solicitacao> filtrar(SolicitacaoFilter solicitacaoFilter, Pageable pageable);
	public Page<ResumoSolicitacao> resumir(SolicitacaoFilter solicitacaoFilter, Pageable pageable);
	
}