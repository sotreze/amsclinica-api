package com.example.ams.api.repository.exame;


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

import com.example.ams.api.model.Medico_;
import com.example.ams.api.model.Paciente_;
import com.example.ams.api.model.TipoExame_;
import com.example.ams.api.model.Exame_;
import com.example.ams.api.model.Exame;
import com.example.ams.api.repository.filter.ExameFilter;
import com.example.ams.api.repository.projection.ResumoExame;

public class ExameRepositoryImpl implements ExameRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public Page<Exame> filtrar(ExameFilter exameFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Exame> criteria = builder.createQuery(Exame.class);
		Root<Exame> root = criteria.from(Exame.class);

		Predicate[] predicates = criarRestricoes(exameFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Exame> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(exameFilter));
	}


	@Override
	public Page<ResumoExame> resumir(ExameFilter exameFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoExame> criteria = builder.createQuery(ResumoExame.class);
		Root<Exame> root = criteria.from(Exame.class);

		criteria.select(builder.construct(ResumoExame.class
				, root.get(Exame_.codigo) 
				, root.get(Exame_.medico).get(Medico_.nome)
				, root.get(Exame_.paciente).get(Paciente_.nome)
				, root.get(Exame_.tipoExame).get(TipoExame_.nome)
				, root.get(Exame_.descricao)
				, root.get(Exame_.data)
				));

		Predicate[] predicates = criarRestricoes(exameFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoExame> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(exameFilter));
	}

	private Predicate[] criarRestricoes(ExameFilter exameFilter, CriteriaBuilder builder,
			Root<Exame> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(exameFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.descricao)), "%" + exameFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(exameFilter.getMedico())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.medico).get(Medico_.nome)), "%" + exameFilter.getMedico().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(exameFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.paciente).get(Paciente_.nome)), "%" + exameFilter.getPaciente().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(exameFilter.getTipoExame())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.tipoExame).get(TipoExame_.nome)), "%" + exameFilter.getTipoExame().toLowerCase() + "%"));
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

	private Long total(ExameFilter exameFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Exame> root = criteria.from(Exame.class);

		Predicate[] predicates = criarRestricoes(exameFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
