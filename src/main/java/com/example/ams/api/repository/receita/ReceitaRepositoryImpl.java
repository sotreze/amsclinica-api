package com.example.ams.api.repository.receita;

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

//import com.example.ams.api.dto.ReceitaGerar;
import com.example.ams.api.model.Medicacao_;
import com.example.ams.api.model.Medico_;
import com.example.ams.api.model.Paciente_;
import com.example.ams.api.model.Receita;
import com.example.ams.api.model.Receita_;
import com.example.ams.api.repository.filter.ReceitaFilter;
import com.example.ams.api.repository.projection.ResumoReceita;

public class ReceitaRepositoryImpl implements ReceitaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public Page<Receita> filtrar(ReceitaFilter receitaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Receita> criteria = builder.createQuery(Receita.class);
		Root<Receita> root = criteria.from(Receita.class);

		Predicate[] predicates = criarRestricoes(receitaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Receita> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(receitaFilter));
	}


	@Override
	public Page<ResumoReceita> resumir(ReceitaFilter receitaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoReceita> criteria = builder.createQuery(ResumoReceita.class);
		Root<Receita> root = criteria.from(Receita.class);

		criteria.select(builder.construct(ResumoReceita.class
				, root.get(Receita_.codigo) 
				, root.get(Receita_.medico).get(Medico_.nome)
				, root.get(Receita_.paciente).get(Paciente_.nome)
				, root.get(Receita_.medicacao).get(Medicacao_.nomeReferencia)
				, root.get(Receita_.descricao)
				));

		Predicate[] predicates = criarRestricoes(receitaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoReceita> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(receitaFilter));
	}

	private Predicate[] criarRestricoes(ReceitaFilter receitaFilter, CriteriaBuilder builder,
			Root<Receita> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(receitaFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Receita_.descricao)), "%" + receitaFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(receitaFilter.getMedico())) {
			predicates.add(builder.like(
					builder.lower(root.get(Receita_.medico).get(Medico_.nome)), "%" + receitaFilter.getMedico().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(receitaFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Receita_.paciente).get(Paciente_.nome)), "%" + receitaFilter.getPaciente().toLowerCase() + "%"));
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

	private Long total(ReceitaFilter receitaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Receita> root = criteria.from(Receita.class);

		Predicate[] predicates = criarRestricoes(receitaFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
