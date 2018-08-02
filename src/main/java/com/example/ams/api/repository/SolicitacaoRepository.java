package com.example.ams.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Solicitacao;
import com.example.ams.api.repository.solicitacao.SolicitacaoRepositoryQuery;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>, SolicitacaoRepositoryQuery {
	
	List<Solicitacao> findByDataGreaterThanEqual(LocalDate data);

}
