package com.example.ams.api.repository.agenda;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Agenda;
import com.example.ams.api.model.Agenda_;
import com.example.ams.api.repository.filter.AgendaFilter;
import com.example.ams.api.repository.projection.ResumoAgenda;

public class AgendaRepositoryImpl implements AgendaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Agenda> filtrar(AgendaFilter agendaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Agenda> criteria = builder.createQuery(Agenda.class);
		Root<Agenda> root = criteria.from(Agenda.class);

		Predicate[] predicates = criarRestricoes(agendaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Agenda> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(agendaFilter));
	}


	@Override
	public Page<ResumoAgenda> resumir(AgendaFilter agendaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoAgenda> criteria = builder.createQuery(ResumoAgenda.class);
		Root<Agenda> root = criteria.from(Agenda.class);

		criteria.select(builder.construct(ResumoAgenda.class
				, root.get(Agenda_.codigo)
				, root.get(Agenda_.ativo)
				, root.get(Agenda_.data)
				, root.get(Agenda_.hora)));


		Predicate[] predicates = criarRestricoes(agendaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoAgenda> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(agendaFilter));
	}

	private Predicate[] criarRestricoes(AgendaFilter agendaFilter, CriteriaBuilder builder,
			Root<Agenda> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (agendaFilter.getDataDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Agenda_.data), agendaFilter.getDataDe()));
		}

		if (agendaFilter.getDataAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Agenda_.data), agendaFilter.getDataAte()));
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

	private Long total(AgendaFilter agendaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Agenda> root = criteria.from(Agenda.class);

		Predicate[] predicates = criarRestricoes(agendaFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
