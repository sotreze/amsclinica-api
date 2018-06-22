package com.example.ams.api.repository.periodo;

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

import com.example.ams.api.model.Periodo;
import com.example.ams.api.model.Periodo_;
import com.example.ams.api.model.Agenda_;
import com.example.ams.api.repository.filter.PeriodoFilter;
import com.example.ams.api.repository.projection.ResumoPeriodo;

public class PeriodoRepositoryImpl implements PeriodoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Periodo> filtrar(PeriodoFilter periodoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Periodo> criteria = builder.createQuery(Periodo.class);
		Root<Periodo> root = criteria.from(Periodo.class);

		Predicate[] predicates = criarRestricoes(periodoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Periodo> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(periodoFilter));
	}

	@Override
	public Page<ResumoPeriodo> resumir(PeriodoFilter periodoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPeriodo> criteria = builder.createQuery(ResumoPeriodo.class);
		Root<Periodo> root = criteria.from(Periodo.class);

		criteria.select(builder.construct(ResumoPeriodo.class
				, root.get(Periodo_.codigo), root.get(Periodo_.ativo)
				, root.get(Periodo_.agenda).get(Agenda_.codigo)));

		Predicate[] predicates = criarRestricoes(periodoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoPeriodo> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(periodoFilter));
	}

	private Predicate[] criarRestricoes(PeriodoFilter periodoFilter, CriteriaBuilder builder,
			Root<Periodo> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(periodoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Periodo_.nome)), "%" + periodoFilter.getNome().toLowerCase() + "%"));
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

	private Long total(PeriodoFilter periodoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Periodo> root = criteria.from(Periodo.class);

		Predicate[] predicates = criarRestricoes(periodoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
