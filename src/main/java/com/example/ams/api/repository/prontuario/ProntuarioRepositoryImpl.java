package com.example.ams.api.repository.prontuario;

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

import com.example.ams.api.model.Paciente_;
import com.example.ams.api.model.Prontuario;
import com.example.ams.api.model.Prontuario_;
import com.example.ams.api.repository.filter.ProntuarioFilter;
import com.example.ams.api.repository.projection.ResumoProntuario;

public class ProntuarioRepositoryImpl implements ProntuarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Prontuario> filtrar(ProntuarioFilter prontuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Prontuario> criteria = builder.createQuery(Prontuario.class);
		Root<Prontuario> root = criteria.from(Prontuario.class);

		Predicate[] predicates = criarRestricoes(prontuarioFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Prontuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(prontuarioFilter));
	}


	@Override
	public Page<ResumoProntuario> resumir(ProntuarioFilter prontuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoProntuario> criteria = builder.createQuery(ResumoProntuario.class);
		Root<Prontuario> root = criteria.from(Prontuario.class);

		criteria.select(builder.construct(ResumoProntuario.class
				, root.get(Prontuario_.codigo)
				, root.get(Prontuario_.receita)
				, root.get(Prontuario_.relatorio)));

		Predicate[] predicates = criarRestricoes(prontuarioFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoProntuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(prontuarioFilter));
	}

	private Predicate[] criarRestricoes(ProntuarioFilter prontuarioFilter, CriteriaBuilder builder,
			Root<Prontuario> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(prontuarioFilter.getRelatorio())) {
			predicates.add(builder.like(
					builder.lower(root.get(Prontuario_.relatorio)), "%" + prontuarioFilter.getRelatorio().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(prontuarioFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Prontuario_.paciente).get(Paciente_.nome)), "%" + prontuarioFilter.getPaciente().toLowerCase() + "%"));
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

	private Long total(ProntuarioFilter prontuarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Prontuario> root = criteria.from(Prontuario.class);

		Predicate[] predicates = criarRestricoes(prontuarioFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}