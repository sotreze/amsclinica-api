package com.example.ams.api.repository.medico;

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

import com.example.ams.api.model.Medico;
import com.example.ams.api.model.Medico_;
import com.example.ams.api.model.Pessoa_;
import com.example.ams.api.repository.filter.MedicoFilter;
import com.example.ams.api.repository.projection.ResumoMedico;

public class MedicoRepositoryImpl implements MedicoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Medico> filtrar(MedicoFilter medicoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Medico> criteria = builder.createQuery(Medico.class);
		Root<Medico> root = criteria.from(Medico.class);

		Predicate[] predicates = criarRestricoes(medicoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Medico> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(medicoFilter));
	}


	@Override
	public Page<ResumoMedico> resumir(MedicoFilter medicoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoMedico> criteria = builder.createQuery(ResumoMedico.class);
		Root<Medico> root = criteria.from(Medico.class);

		criteria.select(builder.construct(ResumoMedico.class
				, root.get(Medico_.codigo), root.get(Medico_.especializacao)
				, root.get(Medico_.pessoa).get(Pessoa_.nome)));

		Predicate[] predicates = criarRestricoes(medicoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoMedico> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(medicoFilter));
	}

	private Predicate[] criarRestricoes(MedicoFilter medicoFilter, CriteriaBuilder builder,
			Root<Medico> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(medicoFilter.getEspecializacao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Medico_.especializacao)), "%" + medicoFilter.getEspecializacao().toLowerCase() + "%"));
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

	private Long total(MedicoFilter medicoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Medico> root = criteria.from(Medico.class);

		Predicate[] predicates = criarRestricoes(medicoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
