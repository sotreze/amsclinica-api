package com.example.ams.api.repository.solicitacao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.ams.api.model.Solicitacao;
import com.example.ams.api.model.Solicitacao_;
import com.example.ams.api.model.Paciente_;
import com.example.ams.api.repository.filter.SolicitacaoFilter;
import com.example.ams.api.repository.projection.ResumoSolicitacao;


public class SolicitacaoRepositoryImpl implements SolicitacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
			
	
	@Override
	public Page<Solicitacao> filtrar(SolicitacaoFilter solicitacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteria = builder.createQuery(Solicitacao.class);
		Root<Solicitacao> root = criteria.from(Solicitacao.class);

		Predicate[] predicates = criarRestricoes(solicitacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Solicitacao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(solicitacaoFilter));
	}


	@Override
	public Page<ResumoSolicitacao> resumir(SolicitacaoFilter solicitacaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoSolicitacao> criteria = builder.createQuery(ResumoSolicitacao.class);
		Root<Solicitacao> root = criteria.from(Solicitacao.class);

		criteria.select(builder.construct(ResumoSolicitacao.class
				, root.get(Solicitacao_.codigo)
				, root.get(Solicitacao_.descricao)
				, root.get(Solicitacao_.dataSolicitacao)
				, root.get(Solicitacao_.paciente).get(Paciente_.nome)
				, root.get(Solicitacao_.email)));


		Predicate[] predicates = criarRestricoes(solicitacaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoSolicitacao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(solicitacaoFilter));
	}
	

	private Predicate[] criarRestricoes(SolicitacaoFilter solicitacaoFilter, CriteriaBuilder builder,
			Root<Solicitacao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		
		if (solicitacaoFilter.getCodigo() != null) {
			predicates.add(
				builder.equal(root.get(Solicitacao_.codigo), solicitacaoFilter.getCodigo()));
		}
		
		if (!StringUtils.isEmpty(solicitacaoFilter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get(Solicitacao_.email)), "%" + solicitacaoFilter.getEmail().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(solicitacaoFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Solicitacao_.paciente).get(Paciente_.nome)), "%" + solicitacaoFilter.getPaciente().toLowerCase() + "%"));
		}
		
		
		if (solicitacaoFilter.getDataSolicitacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Solicitacao_.dataSolicitacao), solicitacaoFilter.getDataSolicitacaoDe()));
		}
		
		if (solicitacaoFilter.getDataSolicitacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Solicitacao_.dataSolicitacao), solicitacaoFilter.getDataSolicitacaoAte()));
		}	
	
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(SolicitacaoFilter solicitacaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Solicitacao> root = criteria.from(Solicitacao.class);

		Predicate[] predicates = criarRestricoes(solicitacaoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
